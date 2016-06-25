organization := "org.salesforce"
name := "force-scala-lib"
version := "1.0.2"
scalaVersion := "2.11.6"
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.1"
libraryDependencies += "org.skife.com.typesafe.config" % "typesafe-config" % "0.3.0"
libraryDependencies += "com.google.code.gson" % "gson" % "1.7.1"

publishMavenStyle := true
pomIncludeRepository := { _ => false }
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}


pomExtra := (
  <url>https://github.com/rajdeepd/force-scala-lib</url>
  <licenses>
    <license>
      <name>Apache License 2.0</name>
      <url>http://opensource.org/licenses/Apache-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:rajdeepd/force-scala-lib.git</url>
    <connection>scm:git:git@github.com:rajdeepd/force-scala-lib.git</connection>
  </scm>
  <developers>
    <developer>
      <id>rajdeepd</id>
      <name>Rajdeep Dua</name>
      <url>http://www.clouddatafacts.com</url>
    </developer>
  </developers>)
