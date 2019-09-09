# UniJ: Universal JDK API Facade

[![Build (Linux)](https://img.shields.io/travis/com/tlinkowski/UniJ/master?logo=linux)](https://travis-ci.com/tlinkowski/UniJ)
[![Build (Windows)](https://img.shields.io/appveyor/ci/tlinkowski/UniJ/master?logo=windows)](https://ci.appveyor.com/project/tlinkowski/UniJ/branch/master)
[![Code coverage](https://img.shields.io/codecov/c/github/tlinkowski/UniJ)](https://codecov.io/gh/tlinkowski/UniJ)
[![Codacy grade](https://img.shields.io/codacy/grade/dce6004af0d44fb7939ec3f377fe0bbe)](https://app.codacy.com/project/tlinkowski/UniJ/dashboard)

[![Maven Central](https://img.shields.io/maven-central/v/pl.tlinkowski.unij/pl.tlinkowski.unij.api?label=Maven%20Central)](https://search.maven.org/search?q=g:pl.tlinkowski.unij)
[![Javadocs](https://javadoc.io/badge/pl.tlinkowski.unij/pl.tlinkowski.unij.api.svg?color=blue)](https://javadoc.io/doc/pl.tlinkowski.unij/pl.tlinkowski.unij.api)
[![Semantic Versioning](https://img.shields.io/badge/-semantic%20versioning-333333)](https://semver.org/)
[![Automated Release Notes by gren](https://img.shields.io/badge/%F0%9F%A4%96-release%20notes-00B2EE)](https://github-tools.github.io/github-release-notes/)

UniJ is a **facade** for:

1.  unmodifiable [`List`](https://docs.oracle.com/javase/10/docs/api/java/util/List.html#unmodifiable)/[`Set`](https://docs.oracle.com/javase/10/docs/api/java/util/Set.html#unmodifiable)/[`Map`](https://docs.oracle.com/javase/10/docs/api/java/util/Map.html#unmodifiable)
    factory methods introduced in JDK 9+

2.  some new [`Collector`](https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html)s introduced in
    JDK 10+

**Note**: This library is meant only as a **facade for the official JDK APIs**. This library will **not** introduce
any APIs of its own, and will only introduce new APIs as they're being introduced in new versions of the JDK.

## Analogy

> UniJ is to new parts of JDK 9+ API what [SLF4J](https://www.slf4j.org/) is to logging API — a **facade**.

## Motivation

This library has been primarily designed for:

1.  [End users stuck on JDK 8](#end-users-stuck-on-jdk-8)
2.  [Library maintainers targeting JDK 8](#library-maintainers-targeting-jdk-8)

### End Users Stuck on JDK 8

If you're stuck on JDK 8, you can't use JDK 9's
[`List.of()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#of()) and friends.

What can you do?

1.  Depend on [Guava](https://github.com/google/guava) or [Eclipse Collections](https://www.eclipse.org/collections/)
    and program to their proprietary APIs.

2.  Or depend on **UniJ** and program to its [JDK-like API](#user-api).

The problem with option 1 is that you get somewhat far from the JDK 11 API and its [specification](#specification).
If you decide to upgrade to JDK 11 in the future, replacing their collections with JDK 11 ones will **not** be
straightforward.

With option 2, there's no such problem. Just add a dependency on
[`pl.tlinkowski.unij.bundle.jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8),
[`pl.tlinkowski.unij.bundle.guava_jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.guava_jdk8), or
[`pl.tlinkowski.unij.bundle.eclipse_jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.eclipse_jdk8),
and enjoy the JDK 11 API on JDK 8! (you may also need to add certain [external dependencies](#external-dependencies))

In the future, if you want to switch to JDK 11, you'll either:

-   change the dependency to [`pl.tlinkowski.unij.bundle.jdk11`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk11)
-   or remove UniJ altogether (will be a simple matter of replacing all occurrences of `UniLists` with `List`, etc.)

### Library Maintainers Targeting JDK 8

If you're a library maintainer targeting JDK 8, you also can't use JDK 9's
[`List.of()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html#of()) and friends.
Instead, you're probably using:

1.  some [`ArrayList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)s
    and wrapping them using [`Collections.unmodifiableList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html#unmodifiableList(java.util.List))

2.  or some external library providing immutable `List`s, like [Guava](https://github.com/google/guava)
    or [Eclipse Collections](https://www.eclipse.org/collections/)

The problems with these options are as follows:

1.  If your users themselves use JDK 11's, Guava's or Eclipse's `Collection`s:
    -   you're wasting potential by not using the best `Collection` implementations available
    -   you're introducing inconsistency regarding which `Collection` implementations are used in your users' app/library

2.  This would be a really bad option, because — by bundling with an external library — you'd impose a heavy dependency on
    your users. Even worse, if they already used one (e.g. Guava), and you bundled with another (e.g. Eclipse Collections),
    they'd end up with **both**!

To sum up, as a library maintainer, the choice of `Collection` implementations **shouldn't be yours**. It's the same
as with logging - you shouldn't choose the logging backend, and should only program to a **facade** like
[SLF4J](https://www.slf4j.org/). That's *precisely* what UniJ lets you do with respect to `Collection` factory methods.

Simply add an intransitive (Gradle `implementation`) or a transitive (Gradle `api`) dependency on the
extremely lightweight [`pl.tlinkowski.unij.api`](subprojects/api/pl.tlinkowski.unij.api), and inform your users they
should add an intransitive dependency on:

-   an [SFL4J binding](https://www.slf4j.org/manual.html#swapping) of their choosing (UniJ [depends on SLF4J](#slf4j)))
-   a [UniJ binding](#bindings) of their choosing (like with SLF4J)

Alternatively, you can depend on the still quite lightweight
[`pl.tlinkowski.unij.bundle.jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8),
and let your users optionally *override* the default JDK 8 bindings by depending on some other bindings (UniJ supports
multiple competing [bindings](#bindings) at runtime, with the JDK 8 bindings having the lowest priority).

## API

UniJ has two kind of [APIs](subprojects/api):
-   [User API](#user-api): utility classes (this is what the user interacts with)
-   [Service API](#service-api): interfaces (this is what the binding provider implements)

The call chain looks as follows:
```text
end user ⟷ User API ⟷ Service API ⟷ binding provider
```

In other words, the end user is oblivious of the [Service API](#service-api), and the binding provider is oblivious of
the [User API](#user-api).

UniJ is somewhat similar to [SLF4J](https://www.slf4j.org/) (Simple Logging Facade for Java) in that it provides an
API that can be implemented in many different ways and then injected at runtime as a
[Java service](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ServiceLoader.html).

### User API

The User API is defined in [`pl.tlinkowski.unij.api`](subprojects/api/pl.tlinkowski.unij.api) and consists of the
following utility classes:

-   [`UniLists`](subprojects/api/pl.tlinkowski.unij.api/src/main/java/pl/tlinkowski/unij/api/UniLists.java)
    (equivalent to [`List`](https://docs.oracle.com/javase/10/docs/api/java/util/List.html#unmodifiable)'s
    static factory methods)

    -   example usage in
        [`UniListsSpec`](subprojects/api/pl.tlinkowski.unij.api/src/test/groovy/pl/tlinkowski/unij/api/UniListsSpec.groovy)

-   [`UniSets`](subprojects/api/pl.tlinkowski.unij.api/src/main/java/pl/tlinkowski/unij/api/UniSets.java)
    (equivalent to [`Set`](https://docs.oracle.com/javase/10/docs/api/java/util/Set.html#unmodifiable)'s
    static factory methods)

    -   example usage in
        [`UniSetsSpec`](subprojects/api/pl.tlinkowski.unij.api/src/test/groovy/pl/tlinkowski/unij/api/UniListsSpec.groovy)

-   [`UniMaps`](subprojects/api/pl.tlinkowski.unij.api/src/main/java/pl/tlinkowski/unij/api/UniMaps.java) 
    (equivalent to [`Map`](https://docs.oracle.com/javase/10/docs/api/java/util/Map.html#unmodifiable)'s
    static factory methods)

    -   example usage in
        [`UniMapsSpec`](subprojects/api/pl.tlinkowski.unij.api/src/test/groovy/pl/tlinkowski/unij/api/UniListsSpec.groovy)

-   [`UniCollectors`](subprojects/api/pl.tlinkowski.unij.api/src/main/java/pl/tlinkowski/unij/api/UniCollectors.java)
    (equivalent to [`Collectors`](https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html)
    utility class)

    -   example usage in
        [`UniCollectorsSpec`](subprojects/api/pl.tlinkowski.unij.api/src/test/groovy/pl/tlinkowski/unij/api/UniListsSpec.groovy)

The API of these classes has strict equivalence to the corresponding JDK API in terms of:
-   `null` treatment (no `null`s allowed)
-   duplicate handling (e.g. no duplicates allowed in `of` methods of `UniSets` and `UniMaps`)
-   consistency (e.g. only one empty collection instance)

Details of this equivalence can be found in [Specification](#specification) section.

### Service API

*Disclaimer: As an end user, you **don't** need to be concerned with this API. Read on **only if** you want to
implement your own UniJ bindings or want to understand how UniJ works internally.*

UniJ service API is defined in [`pl.tlinkowski.unij.service.api`](subprojects/api/pl.tlinkowski.unij.service.api)
and consists of the following interfaces:

-   `collect`:
    [`UnmodifiableListFactory`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/collect/UnmodifiableListFactory.java),
    [`UnmodifiableSetFactory`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/collect/UnmodifiableSetFactory.java),
    [`UnmodifiableMapFactory`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/collect/UnmodifiableMapFactory.java)

-   `misc`:
    [`MiscellaneousApiProvider`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/misc/MiscellaneousApiProvider.java)

A module providing implementations of one or more of these interfaces constitutes a **binding**.

UniJ provides many predefined bindings, but custom bindings are also possible (see [Bindings](#bindings) for details).

### Why two APIs

Why are there two APIs in UniJ? So that:

1.  for the user, we can exactly mirror the JDK API (equivalent class names, static methods), e.g.:
    -   `List.of()` ⟷ `UniLists.of()`
    -   `Collectors.toUnmodifiableList()` ⟷ `UniCollectors.toUnmodifiableList()`

2.  for the binding provider, we can expose an interface with all the related method to be implemented *together*, e.g.:
    -   `UnmodifiableListFactory.of()`
    -   `UnmodifiableListFactory.collector()`

## Specification

UniJ APIs come with a detailed specification expressed as [Spock](http://spockframework.org/) tests for the
[Service API](#service-api) interfaces.

The specification is based on the contract of the original JDK API (expressed mostly in JavaDoc), and tries to follow
this contract as close as possible.

The specification is expressed as the following test classes defined
[`pl.tlinkowski.unij.test`](subprojects/pl.tlinkowski.unij.test):

-   `collect`:
    [`UnmodifiableListFactorySpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/collect/UnmodifiableListFactorySpec.groovy),
    [`UnmodifiableSetFactorySpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/collect/UnmodifiableSetFactorySpec.groovy),
    [`UnmodifiableMapFactorySpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/collect/UnmodifiableMapFactorySpec.groovy)

-   `misc`:
    [`MiscellaneousApiProviderSpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/misc/MiscellaneousApiProviderSpec.groovy)

Read the specs linked above to learn in detail what contract UniJ follows.

## Bindings

A binding is an implementation of the [Service API](#service-api) available at runtime. If multiple bindings with
the same functionality are present on the runtime classpath/modulepath, the one with the top
[priority](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/UniJService.java)
(lowest number) will be selected.

### Predefined Bindings

UniJ comes with a number of predefined bindings, which can all be found under
[`subprojects/bindings`](subprojects/bindings).

#### Collection Factory API Bindings

UniJ currently provides four types of `Collection` factory API bindings:

1.  JDK 10 ([`pl.tlinkowski.unij.service.collect.jdk10`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk10)):
    simply forwards all calls to the JDK

2.  JDK 8 ([`pl.tlinkowski.unij.service.collect.jdk8`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk8)):
    provides regular mutable JDK 8 collections wrapped using [`Collections.unmodifiableList/Set/Map`](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableList-java.util.List-)

3.  [Guava](https://github.com/google/guava) ([`pl.tlinkowski.unij.service.collect.guava`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.guava)):
    provides Guava's [`ImmutableList`](https://guava.dev/releases/28.0-jre/api/docs/com/google/common/collect/ImmutableList.html)/[`ImmutableSet`](https://guava.dev/releases/28.0-jre/api/docs/com/google/common/collect/ImmutableSet.html)/[`ImmutableMap`](https://guava.dev/releases/28.0-jre/api/docs/com/google/common/collect/ImmutableMap.html)
    implementations (Guava as compile-only dependency — the user needs to add their own runtime dependency on it)

4.  [Eclipse Collections](https://www.eclipse.org/collections/) ([`pl.tlinkowski.unij.service.collect.eclipse`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.eclipse)):
    provides Eclipse's [`ImmutableList`](https://www.eclipse.org/collections/javadoc/10.0.0/org/eclipse/collections/api/list/ImmutableList.html)/[`ImmutableSet`](https://www.eclipse.org/collections/javadoc/10.0.0/org/eclipse/collections/api/set/ImmutableSet.html)/[`ImmutableMap`](https://www.eclipse.org/collections/javadoc/10.0.0/org/eclipse/collections/api/map/ImmutableMap.html)
    implementations (Eclipse as compile-only dependency — the user needs to add their own runtime dependency on it)

#### Miscellaneous API Bindings

UniJ currently provides two types of miscellaneous API bindings:

1.  JDK 11 ([`pl.tlinkowski.unij.service.misc.jdk11`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11)):
    simply forwards all calls to the JDK

2.  JDK 8 ([`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)):
    provides custom implementations based on the ones in JDK 11

### Custom Bindings

You can provide custom bindings by:

-   implementing an interface from the [Service API](#service-api)

-   annotating it with a special [`@UniJService`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/UniJService.java)
    annotation

-   providing a `module-info.java` entry (for JDK 9+) and/or a `META-INF` entry (for JDK 8; I recommend Google's
    [`@AutoService`](https://github.com/google/auto/tree/master/service) for it)

Example:

```java
@UniJService(priority = 1)
@AutoService(UnmodifiableListFactory.class)
public class CustomUnmodifiableListFactory implements UnmodifiableListFactory {
  // ...
}
```

When providing a custom service implementation, one should also create the following Spock test for it:

```groovy
class CustomUnmodifiableListFactorySpec extends UnmodifiableListFactorySpec {

  def setupSpec() {
    factory = new CustomUnmodifiableListFactory()
  }
}
```

A test dependency on [`pl.tlinkowski.unij.test`](subprojects/pl.tlinkowski.unij.test)
is needed for it.

## Bundles

A UniJ bundle is a module having no source (save for its `module-info.java`) and depending on the following three modules:

1.  [`pl.tlinkowski.unij.api`](subprojects/api/pl.tlinkowski.unij.api) module (transitive dependency)
2.  `Collection` factory API binding (= one of `pl.tlinkowski.unij.service.collect.___` modules)
3.  miscellaneous API binding (= one of `pl.tlinkowski.unij.service.misc.___` modules)

### Predefined Bundles

Currently, UniJ provides the following four [bundles](subprojects/bundles):

1.  JDK 11 ([`pl.tlinkowski.unij.bundle.jdk11`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk11)):
    -   [`pl.tlinkowski.unij.service.collect.jdk10`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk10)
    -   [`pl.tlinkowski.unij.service.misc.jdk11`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11)

2.  pure JDK 8 ([`pl.tlinkowski.unij.bundle.jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8)):
    -   [`pl.tlinkowski.unij.service.collect.jdk8`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk8)
    -   [`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)

3.  Guava on JDK 8 ([`pl.tlinkowski.unij.bundle.guava_jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.guava_jdk8)):
    -   [`pl.tlinkowski.unij.service.collect.guava`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.guava)
    -   [`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)

4.  Eclipse on JDK 8 ([`pl.tlinkowski.unij.bundle.eclipse_jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.eclipse_jdk8)):
    -   [`pl.tlinkowski.unij.service.collect.eclipse`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.eclipse)
    -   [`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)

## External Dependencies

### SLF4J

UniJ [User API](#user-api) has an `implementation` dependency on [SLF4J](https://www.slf4j.org/) API to let you have
insight into which implementation it chooses for each of its [Service API](#service-api) interfaces.

As a result, if you use UniJ, you may also add a dependency on one of
[its bindings](https://www.slf4j.org/manual.html#swapping).
Otherwise, you'll see the following message at runtime:

> SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
>
> SLF4J: Defaulting to no-operation (NOP) logger implementation
>
> SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

If your project is:

-   unmodularized: just add a `runtimeOnly` dependency
-   modularized: add an `implementation` dependency + `requires org.slf4j.___;` entry to your `module-info.java`

### Guava / Eclipse Collections

Note that the dependencies on [Guava](https://github.com/google/guava) and
[Eclipse Collections](https://www.eclipse.org/collections/) (present only in modules with `guava` and `eclipse` in
their name, respectively) are `compileOnly` dependencies.

Thanks to this, dependency on UniJ won't affect the version of Guava / Eclipse Collections you want to use, since
you have to declare this dependency explicitly.

If your project is:

-   unmodularized: just add a `runtimeOnly` / `implementation` dependency (depending on whether you'll use it explicitly)
-   modularized: add an `implementation` dependency + `requires ___;` entry to your `module-info.java`

## Kotlin Interoperability

This library is highly interoperable with [Kotlin](https://kotlinlang.org/) thanks to being annotated with regard to:

-   nullability ([`@NonNullPackage`](https://github.com/tlinkowski/basic-annotations/blob/master/subprojects/pl.tlinkowski.annotation.basic/src/main/java/pl/tlinkowski/annotation/basic/NonNullPackage.java))
-   mutability ([`@ReadOnly`](https://github.com/JetBrains/kotlin/blob/master/libraries/tools/kotlin-annotations-jvm/src/kotlin/annotations/jvm/ReadOnly.java))

using annotations provided by [Basic Java Annotations](https://github.com/tlinkowski/basic-annotations) library.

## Performance

If you wonder how UniJ's indirection (= its two extra layers: [User API](#user-api) and [Service API](#service-api))
affects performance, the answer is short: **it effectively doesn't**.

It turns out the JIT compiler simply optimizes all the indirection away.

You can verify this by running a JMH benchmark
([`UniListsBenchmark`](subprojects/api/pl.tlinkowski.unij.api/src/jmh/java/pl/tlinkowski/unij/api/UniListsBenchmark.java))
where calls to `UniLists` (with a JDK 11 binding) are compared to direct JDK 11 API calls. The exact results can be
found [here](docs/UniListsBenchmark-results.txt).

## Requirements

Usage: JDK 8+.

Building: Gradle 5+, JDK 11+.

## About the Author

See my webpage ([tlinkowski.pl](https://tlinkowski.pl/)) or
find me on Twitter ([@t_linkowski](https://twitter.com/t_linkowski)).
