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












