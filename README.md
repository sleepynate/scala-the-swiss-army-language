# Scala: The Swiss Army Language

This repository contains the executable slides for the OSCON 2016 talk, Scala: The Swiss Army Language.

To run the slides, from the repository root directory, run:

``` shell
sbt console -Dscala.color -language:_ -nowarn -i
```

Once the Scala REPL has started, import and instantiate the slides for the appropriate section of the talk. For example, the start the slides for section 4, enter the following:

``` scala
import scala.swiss_army_language.Part4
val replesent=Part4($intp)
```

Lastly, pull the navigation functions into scope for advancing the slides or running the demo code:

``` scala
import replesent._
```

You can use `f` to start the slides, `n` to advance to the next slide, and `r` to run the code on a particular slide.

### Special thanks and more

Additional information about navigating the slides can be found in the repository for [REPLesent](https://github.com/marconilanna/REPLesent), which the presentation software for this talk is based on. Thanks to Marconi Lanna for his awesome project and assistance customizing it for this presentation.
