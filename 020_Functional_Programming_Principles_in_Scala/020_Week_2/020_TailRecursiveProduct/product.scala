
def aggregate(start: Int, aggregator: (Int, Int)=> Int)(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, aggregator(acc, f(a)))
  }
  loop(a, start)
}

def sum: (Int => Int) => (Int, Int) => Int 
  = aggregate(0, _ + _)

def product: (Int => Int) => (Int, Int) => Int 
  = aggregate(1, _ * _)

def fact(i: Int) = product(x => x)(1, i)

