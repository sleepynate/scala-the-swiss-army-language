name := "swiss-army-language"

version := "0.0.1"

scalaVersion := "2.11.7"

libraryDependencies += "org.scala-lang"  % "scala-compiler" % "2.11.7" % Compile

incOptions := incOptions.value.withNameHashing(true)

// Improved dependency management
updateOptions := updateOptions.value.withCachedResolution(true)

// Uncomment to enable offline mode
// offline := true

showSuccess := false

showTiming := true
