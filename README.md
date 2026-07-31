# Master Jetpack Compose Interview Questions

Why is @Composable an annotation?
- @Composable is an annotation that tells the Compose Compiler that this function describes a part of the UI. The compiler generates additional code so the function can participate in Composition and Recomposition.

Why didn't Google create a new keyword instead of an annotation?
* Because annotations allow the Compose Compiler Plugin to identify UI functions at compile time and transform them without changing the Kotlin language itself.

Can a normal function call a composable?
* No. A normal Kotlin function cannot call a composable because it is not part of the Compose Runtime. Only another composable can call a composable.

Why?
-Because composable functions require the Compose Compiler to generate additional runtime information (such as the Composer  parameter), which normal functions do not have.


Can a composable call a normal function?
* Yes. A composable is still a Kotlin function, so it can call normal Kotlin functions whenever needed.

Should we call Repository or API directly from a composable?
* No. Business logic and data fetching should be handled by the ViewModel. The composable should only display UI based on state.

What is the role of the Compose Compiler?
- The Compose Compiler identifies functions marked with @Composable and transforms them into code that works with the Compose Runtime. It generates additional parameters and logic so the function can participate in Composition, Recomposition, and efficient UI updates.

Easy Memory Trick
Remember this sentence:

@Composable -> Compose Compiler -> Compose Runtime -> UI

Whenever someone asks:
"What does the Compose Compiler do?"

Immediately think:
Normal Function -> Transforms -> Composable Function -> Works with Runtime

What is Composition?
* Composition is the process where the Compose Runtime executes composable functions for the first time and builds the UI tree. This is called the Initial Composition.

Follow-up
Interviewer:
Does Composition happen only once?
Expected answer:
No. Initial Composition happens once when the composable enters the Composition. After that, whenever state changes, Compose performs Recomposition for the affected composables.


What is Recomposition?
* Recomposition is the process where the Compose Runtime re-executes only the composables affected by state changes, updating only the necessary parts of the UI.


What triggers Recomposition?
* State changes trigger recomposition. Compose observes state objects, and when a state value changes, it schedules recomposition for the composables that read that state.

Follow-up
Interviewer:
Does every state change recompose the whole screen?
Expected answer:
No. Compose recomposes only the composables that read the changed state.


Why doesn't Compose redraw the whole screen?
* Compose tracks which composables read a particular state. When that state changes, it recomposes only those composables instead of rebuilding the entire screen.


Is Recomposition good or bad?
* Recomposition is a normal and essential part of Compose. It keeps the UI in sync with state. However, unnecessary recompositions can affect performance and should be minimized.

What is Initial Composition?
* Initial Composition is the first execution of a composable when it enters the Composition. During this process, Compose builds the initial UI tree.


How do you think Compose knows that only the counter text should change instead of rebuilding the whole screen?
* Compose keeps track of which composables read a state value. When that state changes, Compose schedules recomposition only for those composables instead of recomposing the entire UI tree.

If only WelcomeMessage changes?
* Only WelcomeMessage() composables reexecute and update UI.
  However, depending on parameters and stability, Compose may also invoke HomeScreen() to determine what changed, while still skipping children that don't need updates.
  This is why you'll sometimes hear:
  "Compose can invoke a parent composable but skip unchanged child composables."

Real Interview Scenario
Interviewer:
Why is Compose called a Declarative UI framework?
A strong answer:
Because in Compose we describe what the UI should look like for a given state instead of manually updating individual UI elements. When the state changes, Compose automatically recomposes the affected parts of the UI.
That's a solid mid-to-senior level answer.

Ultimate Memory Trick
Remember this sentence:
Imperative = Tell the UI what to do.(How)
Declarative = Tell the UI what to be. (What)
XML -> HOW
Compose -> WHAT

What is Imperative UI?
* Imperative UI is an approach where the developer manually tells the system how to update the UI step by step. Every UI change, such as updating text or changing visibility, must be handled explicitly.

What is Declarative UI?
- Declarative UI is an approach where we describe what the UI should look like for the current state. When the state changes, the framework automatically updates the UI.


Why is Compose called Declarative?
* Compose is called declarative because we describe the UI based on state instead of manually updating individual UI elements. When the state changes, Compose automatically recomposes the affected UI.

Follow-up Interview Question
Interviewer:
Why don't we manually refresh the screen in Compose?
Expected answer:
Because Compose observes state changes. When observable state changes, it automatically schedules recomposition for the affected composables.



What is the role of State in Declarative UI?
* State is the single source of truth in Compose. Composables read state, and whenever that state changes, Compose recomposes the affected UI to reflect the new state.

Why is Compose easier to maintain than XML?
* UI is always consistent with state.
* Smaller reusable composables improve readability.
* Less chance of forgetting a UI update.


Why don't we call setText() in Compose?
- In Compose, UI is generated from state instead of manipulating Views directly. When the state changes, Compose automatically updates the displayed text, so methods like setText() are unnecessary.

What are the advantages of Declarative UI over Imperative UI?
*  Less boilerplate
*  Fewer manual UI updates
*  Easier maintenance
*  Better readability
*  UI stays synchronized with state
*  Kotlin-only UI development
*  Reusable composables


Interview Challenge (Senior Level)
Suppose an interviewer asks:
"If Compose is declarative, why do we still need Recomposition?"

How would you answer?
Take a moment to think before reading the expected answer.

A strong answer would be:
Because state can change over time. Declarative UI describes what the UI should look like for the current state, and Recomposition is the mechanism Compose uses to update only the affected parts of the UI whenever that state changes.

Notice how Declarative UI describes the programming model, while Recomposition is the runtime mechanism that makes it work.

What is State?
* State is any data that can change over time and affects what is displayed on the UI.
* e.g Counter, Username, loading

Why doesn't a normal variable update the UI?
* A normal variable is not observable. Compose doesn't know when its value changes, so it doesn't trigger recomposition.


What is mutableStateOf?
- mutableStateOf creates an observable state object. When its value changes, Compose is notified and schedules recomposition for composables that read that state.

What is remember?
- remember stores an object in the Composition so it survives recomposition instead of being recreated every time.

Why do we need both remember and mutableStateOf?
- remember preserves the state across recompositions, while mutableStateOf makes the state observable so Compose can trigger recomposition when it changes.

What happens if remember is removed?
- Then state value doesn’t store and every time it new state created and old value gets reset.

What happens if mutableStateOf is removed?
- Then state object won’t be observable object and it doesn’t trigger recomposition.

Does remember survive screen rotation? (Just guess—we'll learn the exact answer later.)
- I guess no.

## **Level 2**
### Lesson 6 - State Reads, State Writes & The Recomposition Cycle

What is a State Read?
* A State Read occurs when a composable accesses the value of an observable state object. Compose records this dependency so it knows which composables depend on that state.

What is a State Write?
* A State Write occurs when the value of an observable state object is modified. This notifies the Compose Runtime, which may schedule recomposition for dependent composables.

How does Compose know which composables depend on a state?
* Compose records which composables read a particular state. When that state changes, the Compose Runtime uses those recorded dependencies to schedule recomposition for the affected composables.


Why doesn't every composable recompose?
* Because Compose tracks state reads. Only composables that have read the changed state become candidates for recomposition. Unaffected composables can be skipped.

Can multiple composables read the same state?
- Yes mutiple composable can read the same state.

If two composables read the same state and that state changes, what happens?
- Then compose schedules recompostion for those two composables and provide updated UI.

What is the difference between reading state and writing state?
* State Read means a composable accesses a state value, allowing Compose to record the dependency. State Write means the state value is modified, which notifies the Compose Runtime and may trigger recomposition.

### Lesson 7  - remember vs rememberSaveable

Your Compose counter resets every time the user rotates the phone. How would you debug it, identify the cause, and fix it?
* First, verify where the counter state is stored.
* If it's using remember, I know it only survives recomposition, not Activity recreation.
* Screen rotation recreates the Activity, so the Composition is recreated and the remembered state is lost.
* If this is temporary UI state like a counter or search text, replace remember with rememberSaveable.
* If the state belongs to the screen's business logic or comes from an API, move it to a ViewModel instead.


Why does remember lose its value after screen rotation?
- Remember stores state value in current composition when initially actiivity created. So after screen rotation current activity  gets destroyed and new activity created so new compostion happens so previous value remeber looses.

What is Activity recreation?
* Activity recreation is the process where Android destroys the current Activity and creates a new instance because of a configuration change (such as screen rotation or language change).

What is rememberSaveable?
- RememberSavable stores value in android saved state and survies configuration changes.

How is rememberSaveable different from remember?
- remeberSavable survies both recomposition and  configuration change and remember survies only recomposition.

Should API responses be stored using rememberSaveable? Why?
- No API reponses should not be stored using rememberSavable.
* API data is usually large.
* It belongs to the business/data layer.
* rememberSaveable is intended for small UI state.
* ViewModel is the appropriate place because it survives configuration changes and separates UI from business logic.


Name five examples where rememberSaveable is a good choice.
* Search text
* Selected tab
* Current page in a pager
* Counter value
* Form input (name, email)
* Selected filter
* Scroll position

Can rememberSaveable save every object? If not, why?
- It cannot automatically save every object. It supports types that Android's saved state can handle. For custom objects, you need to provide a Saver or use another appropriate state holder.

### Lesson 8  - Stateful vs Stateless Composables(State Hoisting)


What is a Stateful Composable?
* A Stateful Composable owns and manages its own state internally.

What is a Stateless Composable?
- Statelss Composables doesn’t own state and it receives data through parameters and communicates through events callback

What is State Hoisting?
* State Hoisting is the process of moving state ownership from a child composable to its parent composable, passing state down and events back up through callbacks.


Why does Google recommend Stateless Composables?
- Because it doesn’t own any state so it can reusable and help in making consistent UI
- Reusable
- Easier to test
- Better separation of concerns
- Easier to maintain


Explain the sentence: "State flows down, Events flow up."
- State flows down - pass state from parent to child in downward direction
- Events flow up - when event happen then callback executed in upward direction

Should every state be hoisted? Why or why not?
* No. Small UI-only state such as password visibility, animation progress, or expanded/collapsed state can remain inside the composable. Hoist state only when it needs to be shared or controlled by a parent.

Give three examples where state should remain inside a composable.
* Password visibility
* Expanded card
* Tooltip visibility
* Animation state
* Dropdown expanded state

Give three examples where state should be hoisted.
* Search query shared with a movie list
* Login form (username & password managed by the parent)
* Selected tab used by multiple composables
* Current page in a pager
* Shopping cart quantity displayed in multiple places

### Compose Rule #2

You learned your first important Compose rule yesterday:

State flows down, Events flow up.

Here's the second one:

State should be owned by the Lowest Common Ancestor (LCA) of every composable that needs it.

![Revision1](screenshots/level1_level2.png)

### Lesson 9  - Modifier Deep Dive


What is a Modifier?
* Modifier is an immutable object used to decorate, configure, or add behaviour to a composable without modifying the composable itself.

Why did Google introduce Modifier?
- Instead of adding to much parameter in constructor of composables it is not reusable and hard to maintain.
* Reusability
* Separation of concerns
* Cleaner APIs


Is Modifier mutable or immutable?
- No, Modifier is immutable.

What is a Modifier chain?
* A Modifier Chain is a sequence of immutable modifier elements where each modifier wraps the previous one to add layout, drawing, or interaction behaviour.

Why does Modifier order matter?
- Each modifier wraps the previous modifier, so measurement, drawing, and input handling happen according to the chain.

Does Modifier change the composable itself?
- No it doesn’t change composable itself it just use to decorate and add behavoir to composable

Why is Modifier reuse recommended?
- Because on every propery when use in chaning then it creates new object always, so it can affect to perfomance as well.

Give five commonly used modifiers.
* fillMaxWidth()
* fillMaxHeight()
* fillMaxSize()
* padding()
* size()
* background()
* clickable()
* clip()

Modifier
.padding(20.dp)
.clickable()

↓

Clickable -> Padding ->Text

✅ Entire padded area is clickable


Modifier
.clickable()
.padding(20.dp)

↓

Padding ->Clickable->Text

❌ Only Text area is clickable

### Lesson 10  - Compose Layout System (Constraints, Measure & Layout)


What are the three phases of the Compose Layout System?
- Three phases of compose layout system are measure, layout, draw

What are Constraints?
- Constraint defines minimum & maximum width & height parent allow to child to occupy.

What happens during the Measure Phase?
- Measure phase is where composable calculates its desired width & height within the given constraints.

What happens during the Layout Phase?
- Layout phase where composable defines where to place composable on screen i.e calculate x,y coordinate for composable with calulated measured size.

What happens during the Draw Phase?
- Draw phase where composables draws on the screen.

Why does padding() change size?
* Padding increases the space required by the composable during the Measure phase.


Why doesn't background() change size?
* Background only paints.

How does fillMaxWidth() work?
- It tells child that Occupy the maximum width allowed by the parent constraints.

## Lesson 11 - Row, Column & Box (Compose Layouts)

What happens when you change SpaceEvenly to Center?
- Then it will place in the center of the screen

What happens when you change horizontalAlignment to Alignment.End?
- Then it will place to the right side of the screen

Where are A, B, and C placed?
- Nothing is showin in screen it just blank

Why do the texts overlap?
- Because box stacks children on top of other if we didn’t mention position explicitly

What is a Layout in Compose?
- Layout is a composable whose job is to meause and place it children

How does a Column arrange children?
- Column arranges children vertically.

How does a Row arrange children?
- Row arranges children horizontally

Why does a Box overlap its children?
- Box stacks its children on the top of another

What is the Main Axis?
* The Main Axis is the primary direction in which children are laid out.
* Arrangement is used on the Main Axis

What is the Cross Axis?
* the direction perpendicular to the Main Axis.
* Alignment controls positioning along the Cross Axis.

What is the difference between Arrangement and Alignment?
- Arrangement works with Main Axis and Alignment works with Cross Axis

In a Column, which property controls vertical spacing?
- verticalArrangement

In a Row, which property controls vertical positioning?
- verticalAlignment

When would you choose a Box instead of a Column?
- When we want to place children on top of another

Senior Interview Challenge

Where will A appear?
- It will apper Right side with position y = 0

Where will B appear?
- It will be near the middle  of the Column.

Where will C appear?
- It will be near the bottom of the Column.

Which axis is controlled by Arrangement.SpaceBetween?
- MainAxis

Which axis is controlled by Alignment.End?
- CrossAxis

Explain the Measure → Layout → Draw process for this Column.
- Column -> measured width & height for all its children -> check for positoning all A,B,C -> Draw All A,B,C

Senior Interview Question ———>

Column(
modifier = Modifier.height(300.dp),
verticalArrangement = Arrangement.SpaceEvenly
) {
Text("A")

    Text("B")

    Text("C")
}

If the total height of A, B, and C is 90.dp, how much free space remains?
* Free Space = 300 - 90 = 210.dp

Where does Arrangement.SpaceEvenly place that free space?
- Before, Between and after comosable place

What is the difference between:
* SpaceBetween - It defines space between children
* SpaceAround - Each child gets equal space around itself.
* SpaceEvenly - It defines space before, between & after children place

## Lesson 12 - weight() Deep Dive

What is weight()?
- It tells parent(Row & Column) to distribute remaining allow space to weighted children in propotional ratio

Why did Google introduce weight()?
* To make proportional layouts easier without manually calculating widths or heights.

How does weight() internally distribute space?
- First it calculates fixed measured child size then from remaining space space it will distribute others childs in propotional rato

Why are non-weight children measured first?
- The parent must know how much space is already consumed before it can calculate the remaining space to distribute among weighted children.

Why doesn't weight() work in Box?
- Box dosen’t have any axis.
- It stacks children

Difference between weight() and fillMaxWidth()?
- weight works with Row & coloumn, siblings, available remaining space
- fillMaxWidth works with parents constraints, different type of layouts, doesn’t work with siblings

What is fill = false?
the allocated space still exists, but the child doesn't have to fill it.

Give one real-world use case for weight().
* Equal buttons

## Lesson 13 - Spacer & Empty Space Management

What is a Spacer?
- Spacer is composable whose job is to add empty space in layout

Why did Google introduce Spacer?
- To provide more control over empty space in layout

Does Spacer draw anything?
- No

Which phases does Spacer participate in?
- Measure, Layout & Draw

Difference between Spacer and padding?
- Spacer has it own component & padding belongs component

How does Spacer(weight = 1f) work internally?
- Internally it will check how space remaining after measured for fixed size composable

Difference between Spacer(weight = 1f) and Arrangement.SpaceBetween?
- Spacer(weight = 1f) give more control over empty space in layout & Arrangment.SpaceBetween provide equal space between composable

Give two real-world use cases for Spacer.
- Top and Bottom button
- Chat message layout
- Profile and logut button

## Lesson 14 - BoxWithConstraints Deep Dive

Exercise 1

BoxWithConstraints {

    Text(maxWidth.toString())

}

Where does maxWidth come from?
- It comes from parent constraints.

Can you access it outside BoxWithConstraints?
- No, it will give compiler error

Exercise 2

BoxWithConstraints {

    if(maxWidth < 500.dp){

        Text("Compact")

    }else{

        Text("Expanded")

    }

}


What happens if the parent width is 450.dp?
- Then UI shows for compact size

What happens if the parent width is 700.dp?
- Then UI Show for Expanded size

Exercise 3

Which is better for deciding between phone and tablet layouts?

BoxWithConstraints {
if(maxWidth > 600.dp)
}

why?
- Because BoxConstraints exposes parent contraints to use available space for its content but Local configuration works device , screen type

What is BoxWithConstraints?
- BoxWithConstraints is layout that exposes parent constraints it recieves so that its content can make decisions based on available space for layout

Why did Google introduce it?
* To build adaptive and responsive layouts based on available constraints instead of relying on device-specific checks.

Difference between Box and BoxWithConstraints?
- Box doesn’t expose parent constraints like max/min width & height but BoxWithConstraints does

What are maxWidth and maxHeight?
- maxWidth & maxHeight gives maximum width & height which parent constraint gives

Does BoxWithConstraints change constraints?
- No it doesn’t

Why is BoxWithConstraints better than checking device type?
- Because its can make decision for its content based on available space

Difference between BoxWithConstraints and LocalConfiguration?
- BoxWithConstraints works - parent constraints, space available
- LocalConfiguration works - screen, device type

Give two real-world use cases.
- Netflix Movie poster UI in phone & tablet
- Shopping app item descrpition UI in phone & tablet
- video with comments


Senior Interview Challenge

What is maxWidth?
- 500dp

Which layout will be composed?
- Row layout

Why?
-cause 500dp is greater than 400dp


What happens if the width changes to 350.dp?
- Then coloumn layout will be composed

Explain the Measure → Layout → Draw process for this composable.
* Step 1 — Measure
  Parent sends constraints

↓

BoxWithConstraints receives them

↓

maxWidth = 500.dp becomes available inside the scope

Step 2 — Composition Decision

if(maxWidth > 400.dp)

↓

Compose Row

Notice something important:
The decision about whether to compose a Row or Column happens before measurement of those children.
Then the chosen layout (Row in this case) measures its children.

Step 3 — Layout

Row places Image

↓

Row places Details

Step 4 — Draw
Draw Image

↓

Draw Details


























🧠 Mentor Tip

This lesson introduces an important Compose principle:

Compose layouts should adapt to the space they're given, not make assumptions about the device they're running on.

Think of it this way:

* Row and Column decide how children are arranged.
* weight() decides how remaining space is shared.
* Spacer decides where empty space exists.
* BoxWithConstraints helps your composable decide what UI to build based on the available space.