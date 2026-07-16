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













