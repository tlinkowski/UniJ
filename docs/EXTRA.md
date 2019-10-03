# UniJ: Extra Information

← back to [UniJ README](../README.md)

Table of Contents:

-   [Project Layout](#project-layout)
-   [Performance](#performance)
-   [Kotlin Interoperability](#kotlin-interoperability)
-   [Backport of Java 9+ to Java 8](#backport-of-java-9-to-java-8)

## Project Layout

-   [API](../README.md#API):
    -   [`pl.tlinkowski.unij.api`](../subprojects/api/pl.tlinkowski.unij.api)
    -   [`pl.tlinkowski.unij.service.api`](../subprojects/api/pl.tlinkowski.unij.service.api)

-   [bindings](../README.md#bindings):
    -   `Collection` factories:
        -   [`pl.tlinkowski.unij.service.collect.jdk8`](../subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk8)
        -   [`pl.tlinkowski.unij.service.collect.jdk10`](../subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk10)
        -   [`pl.tlinkowski.unij.service.collect.guava`](../subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.guava)
        -   [`pl.tlinkowski.unij.service.collect.eclipse`](../subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.eclipse)
    -   miscellaneous:
        -   [`pl.tlinkowski.unij.service.misc.jdk8`](../subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)
        -   [`pl.tlinkowski.unij.service.misc.jdk11`](../subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11)

-   [bundles](../README.md#bundles):
    -   [`pl.tlinkowski.unij.bundle.jdk8`](../subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8)
    -   [`pl.tlinkowski.unij.bundle.jdk11`](../subprojects/bundles/pl.tlinkowski.unij.bundle.jdk11)
    -   [`pl.tlinkowski.unij.bundle.guava_jdk8`](../subprojects/bundles/pl.tlinkowski.unij.bundle.guava_jdk8)
    -   [`pl.tlinkowski.unij.bundle.eclipse_jdk8`](../subprojects/bundles/pl.tlinkowski.unij.bundle.eclipse_jdk8)

-   [`pl.tlinkowski.unij.test`](../subprojects/pl.tlinkowski.unij.test)
    (for [custom bindings](../README.md#custom-bindings))

### Performance

If you wonder how UniJ's indirection (= its two extra layers: [User API](../README.md#user-api)
and [Service API](../README.md#service-api)) affects performance, the answer is short: **it effectively doesn't**.

It turns out the JIT compiler simply optimizes all the indirection away.

You can verify this by running a JMH benchmark
([`UniListsBenchmark`](../subprojects/api/pl.tlinkowski.unij.api/src/jmh/java/pl/tlinkowski/unij/api/UniListsBenchmark.java))
where calls to `UniLists` (with a JDK 11 binding) are compared to direct JDK 11 API calls. The exact results can be
found [here](UniListsBenchmark-results.txt).

### Kotlin Interoperability

This library is highly interoperable with [Kotlin](https://kotlinlang.org/) thanks to being annotated with regard to:

-   nullability ([`@NonNullPackage`](https://github.com/tlinkowski/basic-annotations/blob/master/subprojects/pl.tlinkowski.annotation.basic/src/main/java/pl/tlinkowski/annotation/basic/NonNullPackage.java))
-   mutability ([`@ReadOnly`](https://github.com/JetBrains/kotlin/blob/master/libraries/tools/kotlin-annotations-jvm/src/kotlin/annotations/jvm/ReadOnly.java))

using annotations provided by [Basic Java Annotations](https://github.com/tlinkowski/basic-annotations) library.

### Backport of Java 9+ to Java 8

If you're looking for a backport of Java 9+ to Java 8, you can use the following for:

1.  new **APIs**: [UniJ](http://unij.tlinkowski.pl)

    -   UniJ JDK 8 bundle ([`pl.tlinkowski.unij.bundle.jdk8`](../subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8))
        is *effectively* a backport of **some** of the JDK 9+ APIs to JDK 8
        (see [End Users Stuck on JDK 8](../README.md#end-users-stuck-on-jdk-8) for details)

2.  new **language features**: [Jabel](https://github.com/bsideup/jabel)

    -   Jabel is an annotation processor that lets you use **some** language features of Java 9+
        while still targeting JDK 8

3.  **Java Platform Module System**: [Gradle Modules Plugin](https://github.com/java9-modularity/gradle-modules-plugin)

    -   Gradle Modules Plugin provides support for JPMS (`module-info.java`) not only to JDK 9+ projects (standard mode)
        but also to JDK 8 projects (special
        [mixed mode](https://github.com/java9-modularity/gradle-modules-plugin#compilation-to-a-specific-java-release))

Together, UniJ, Jabel, and Gradle Modules Plugin may provide you with pretty good "Java 9+"-like experience
while still targeting / being on JDK 8. 

---

← back to [UniJ README](../README.md)
