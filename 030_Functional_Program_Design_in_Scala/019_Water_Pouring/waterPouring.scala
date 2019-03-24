
class Pouring(capacity: Vector[Int]) {
  
  type State = Vector[Int]

  trait Action extends (State => State)

  case class Empty(glass: Int) extends Action {
    override def apply(source: State) = source.updated(glass, 0)

    override def toString = s"Empty($glass)"
  }

  case class Fill(glass: Int) extends Action {
    override def apply(source: State) = source.updated(glass, capacity(glass))

    override def toString = s"Fill($glass)"
  }

  case class Pour(source: Int, destination: Int) extends Action {
    override def apply(glasses: State) = {
      val diff = (capacity(destination) - glasses(destination)) min glasses(source)

      glasses.updated(destination, glasses(destination) + diff).
        updated(source, glasses(source) - diff)
    }

    override def toString = s"$source -> $destination"
  }
  

  val indices = capacity.indices

  def start: State = (indices map (_ => 0)).toVector

  val actions: Stream[Action] = 
    (indices map (Fill(_))).toStream append
    (indices map (Empty(_))) append 
    (for (source <- indices; dest <- indices; if source != dest) yield Pour(source, dest)) 

  def repeat(src: Stream[(State, List[Action])], visited: => Set[State]): Stream[(State, List[Action])] = {
    lazy val visitedStates = visited ++ (src map (_._1))
    def next:((State, List[Action])) => Stream[(State, List[Action])] = {
      case (state, acts) =>
        actions filter (a => !(visitedStates contains (a(state)))) map
          (action => (action(state), action :: acts)) 
    } 
    lazy val mapped = repeat(src flatMap next, visited ++ visitedStates)
    src append mapped
  }

  lazy val possibilities: Stream[(State, List[Action])] = 
    repeat(Stream((start, List())), Set(start))

  def until(goal: Int) = possibilities.filter(p => !p._1.forall(_ != goal)).head

  override def toString = capacity.toString

  def play(state: State, actions: List[Action]): Unit = actions match {
    case action :: actions => {
      println(state)
      println(action)
      play(action(state), actions)
    }
   case Nil => println(s"Final state: $state")
  }

  def play(pair: (State, List[Action])): Unit = play(start, pair._2.reverse)
}

val p = new Pouring(Vector(4, 9))

