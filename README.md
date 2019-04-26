# buildOptionsTest
Test code for comparing compile vs compileOnly directives

This project contains two largely identical projects. The notable difference is in build.gradle, 
where one uses `compile`, and the other uses `compileOnly`.

The two modules otherwise only differ in package/class names to make it easier to identify which project is inspected.

Build both modules, then compare their Manifest.MF: Contrary to my expectation, 
it doesn't make any difference if `compile` or `compileOnly` is used at build time.

Assumed explanation: Gradle resolves the dependencies and puts them on the classpath. 
When bnd does its job building the Manifest, it doesn't know about any gradle-constraints with regards to the libraries availability.

If that's appropriate: Fine. If not: Where am I wrong?