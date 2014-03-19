... the recursive call in factorial

def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)

is followed by a multiplication. Hence, a new stack frame is allocated for the recursive instance of factorial, and is deallo-
cated after that instance has finished. The given formulation of the factorial func-
tion is not tail-recursive; it needs space proportional to its input parameter for its
execution.
More generally, if the last action of a function is a call to another (possibly the same)
function, only a single stack frame is needed for both functions. Such calls are called
“tail calls”.

def factorial_tail(n: Int): Int = {
  def f(n: Int): Int =
    if (n == 0) 1 else n * f(n - 1)
  f(n)
}
