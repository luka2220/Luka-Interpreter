# Fun Interpreter
This interpreter was derived from the book *Writing an Interpreter in Go* which I followed to get the basic structure for lexical analysis & tokenization, parsing, and evaluation.

---
## Language Features 
The language that I built the interpreter on is a made-up one. However, it has the features of most modern programming languages today with C-like syntax:
  - variable bindings
  - integers & booleans
  - arithmetic expressions
  - built-in functions
  - first-class & higher-order functions
  - closures
  - string data structure
  - array data structure
  - hash data structure

---
## Sample Code
```
let fibonacci = fn(x) {
  if (x == 0) {
    return 0;
  } else {
    if (x == 1) {
      return 1;
    } else {
      fibonacci(x - 1) + fibonacci(x - 2);
    }
  }
};
```
