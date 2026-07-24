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









