# UniJ: Universal JDK 9+ API Facade

[![Build (Linux)](https://img.shields.io/travis/com/tlinkowski/UniJ/master?logo=linux)](https://travis-ci.com/tlinkowski/UniJ)
[![Build (Windows)](https://img.shields.io/appveyor/ci/tlinkowski/UniJ/master?logo=windows)](https://ci.appveyor.com/project/tlinkowski/UniJ/branch/master)
[![Code coverage](https://img.shields.io/codecov/c/github/tlinkowski/UniJ)](https://codecov.io/gh/tlinkowski/UniJ)
[![Codacy grade](https://img.shields.io/codacy/grade/dce6004af0d44fb7939ec3f377fe0bbe)](https://app.codacy.com/project/tlinkowski/UniJ/dashboard)

[![Maven Central](https://img.shields.io/maven-central/v/pl.tlinkowski.unij/pl.tlinkowski.unij.api?label=Maven%20Central)](https://search.maven.org/search?q=g:pl.tlinkowski.unij)
[![Javadocs](https://javadoc.io/badge/pl.tlinkowski.unij/pl.tlinkowski.unij.api.svg?color=blue)](https://javadoc.io/doc/pl.tlinkowski.unij/pl.tlinkowski.unij.api)
[![Semantic Versioning](https://img.shields.io/badge/-semantic%20versioning-333333)](https://semver.org/)
[![Automated Release Notes by gren](https://img.shields.io/badge/%F0%9F%A4%96-release%20notes-00B2EE)](https://github-tools.github.io/github-release-notes/)

## Introduction

UniJ is a JDK 8 **facade** for:

1.  unmodifiable [`List`](https://docs.oracle.com/javase/10/docs/api/java/util/List.html#unmodifiable)/[`Set`](https://docs.oracle.com/javase/10/docs/api/java/util/Set.html#unmodifiable)/[`Map`](https://docs.oracle.com/javase/10/docs/api/java/util/Map.html#unmodifiable)
    factory methods (equivalent to those introduced in JDK 9+)

2.  some new [`Collector`](https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html) providers
    (equivalent to those introduced in JDK 9+)

UniJ is similar to [SLF4J](https://www.slf4j.org/) (Simple Logging Facade for Java) in that it provides an API that
can be implemented in many different ways and then be injected at runtime as a
[Java service](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ServiceLoader.html).

UniJ consists of three key parts described further on: its **API**, its **bindings**, and its **bundles**.

*Note*: if you look for a specific UniJ project, consult [UniJ project layout](docs/EXTRA.md#project-layout).

### Analogy

> UniJ is to new parts of JDK 9+ API what [SLF4J](https://www.slf4j.org/) is to logging API — a **facade**.

### Quick Example

<details open>
<summary>JDK 9+</summary>

```java
Set<Integer> set = Set.of(1, 2);
List<Integer> list = List.of(1, 2, 1);
Map<Integer, String> map = Map.of(1, "a", 2, "b");

Set.copyOf(list); // ⇒ [1, 2]
Set.of(1, 2, 1); // throws "duplicate element" exception
Set.of(1, 2, null); // throws null-pointer exception
```

</details>

<details open>
<summary>UniJ (JDK 8+)</summary>

```java
Set<Integer> set = UniSets.of(1, 2);
List<Integer> list = UniLists.of(1, 2, 1);
Map<Integer, String> map = UniMaps.of(1, "a", 2, "b");

UniSets.copyOf(list); // ⇒ [1, 2]
UniSets.of(1, 2, 1); // throws "duplicate element" exception
UniSets.of(1, 2, null); // throws null-pointer exception
```

</details>

### Notes

1.  UniJ is meant **only** as a facade for the **official JDK APIs**. UniJ will **not** introduce any
    APIs of its own design. UniJ may **only** introduce new APIs that directly correspond to APIs in the latest stable
    release of the JDK (currently, it's [JDK 13](https://openjdk.java.net/projects/jdk/13/)).

2.  UniJ is also a partial:
    -   [backport of JDK 9+ to JDK 8](docs/EXTRA.md#backport-of-java-9-to-java-8)
    -   [proxy for Guava and Eclipse Collections](#collection-factory-api-bindings)

## Method Summary

<table>

  <tr>
    <th colspan="2">JDK 9+</th><th colspan="2">UniJ (JDK 8+)</th>
  </tr>

  <tr>
    <td align="center"><em>type</em></td>
    <td align="center" colspan="2"><em>static method name</em></td>
    <td align="center"><em>type</em></td>
  </tr>

  <tr>
    <td align="center">
      <a href="https://docs.oracle.com/javase/10/docs/api/java/util/List.html#method.summary"><code>List</code></a><br>
      <a href="https://docs.oracle.com/javase/10/docs/api/java/util/Set.html#method.summary"><code>Set</code></a><br>
    </td>
    <td align="center" colspan="2"><code>of</code>, <code>copyOf</code></td>
    <td align="center">
      <a href="https://static.javadoc.io/pl.tlinkowski.unij/pl.tlinkowski.unij.api/0.1.1/pl.tlinkowski.unij.api/pl/tlinkowski/unij/api/UniLists.html#method.summary"><code>UniLists</code></a><br>
      <a href="https://static.javadoc.io/pl.tlinkowski.unij/pl.tlinkowski.unij.api/0.1.1/pl.tlinkowski.unij.api/pl/tlinkowski/unij/api/UniSets.html#method.summary"><code>UniSets</code></a><br>
    </td>
  </tr>

  <tr>
    <td align="center">
      <a href="https://docs.oracle.com/javase/10/docs/api/java/util/Map.html#method.summary"><code>Map</code></a>
    </td>
    <td align="center" colspan="2">
      <code>of</code>, <code>copyOf</code>,<br>
      <code>entry</code>, <code>ofEntries</code>
    </td>
    <td align="center">
      <a href="https://static.javadoc.io/pl.tlinkowski.unij/pl.tlinkowski.unij.api/0.1.1/pl.tlinkowski.unij.api/pl/tlinkowski/unij/api/UniMaps.html#method.summary"><code>UniMaps</code></a>
    </td>
  </tr>

  <tr>
    <td align="center">
      <a href="https://docs.oracle.com/javase/10/docs/api/java/util/stream/Collectors.html#method.summary"><code>Collectors</code></a>
    </td>
    <td align="center" colspan="2">
      <code>toUnmodifiableList</code><br>
      <code>toUnmodifiableSet</code><br>
      <code>toUnmodifiableMap</code><br>
      <code>filtering</code><br>
      <code>flatMapping</code>
    </td>
    <td align="center">
      <a href="https://static.javadoc.io/pl.tlinkowski.unij/pl.tlinkowski.unij.api/0.1.1/pl.tlinkowski.unij.api/pl/tlinkowski/unij/api/UniCollectors.html#method.summary"><code>UniCollectors</code></a>
    </td>
  </tr>

</table>

## Motivation

This library has been designed primarily for:

1.  [End Users Stuck on JDK 8](#end-users-stuck-on-jdk-8)
2.  [Libraries Targeting JDK 8](#libraries-targeting-jdk-8)

### End Users Stuck on JDK 8

If you're stuck on JDK 8, you can't use JDK 9+'s [new methods](#method-summary) like `List.of`, etc.

However, by adding a dependency on a UniJ bundle of your choosing (plus some optional extra dependencies),
you can enjoy a JDK 11-like API on JDK 8!

**See**:
-   [Usage for End Users](docs/USAGE.md#implementation-for-end-users)
-   [Q&A for End Users](docs/Q-and-A.md#end-user-qa)
-   [sample end-user projects](subprojects/samples/enduser)

### Libraries Targeting JDK 8

If you maintain a library targeting JDK 8, you can't use JDK 9+'s [new methods](#method-summary) like `List.of`, etc.

However, by adding a dependency on UniJ [User API](#user-api), you can program to JDK 11-like API!

*Note*: your users will have to provide implementations of the above-mentioned API
(as per [Usage for End Users](docs/USAGE.md#implementation-for-end-users)).

**See**:
-   [Usage for Library Providers](docs/USAGE.md#user-api-for-library-providers)
-   [Q&A for Library Providers](docs/Q-and-A.md#library-provider-qa)
-   [sample library projects](subprojects/samples/lib)

## API

UniJ has two kind of [APIs](subprojects/api):
-   [User API](#user-api): utility classes (this is what the user interacts with)
-   [Service API](#service-api): interfaces (this is what the service provider implements)

The call chain looks as follows:
```text
end user ⟷ User API ⟷ Service API ⟷ service provider
```

In other words, the end user is oblivious of the [Service API](#service-api),
and the service provider is oblivious of the [User API](#user-api).

### User API

The User API is defined in [`pl.tlinkowski.unij.api`](subprojects/api/pl.tlinkowski.unij.api) and consists of the
following utility classes: `UniLists`, `UniSets`, `UniMaps` and `UniCollectors`
(see [Method Summary](#method-summary) for details).

This API has strict equivalence to the corresponding JDK API (see [API Specification](#api-specification) for details).

### Service API

*Disclaimer: As an end user, you **don't** need to be concerned with this API.*

UniJ Service API is defined in [`pl.tlinkowski.unij.service.api`](subprojects/api/pl.tlinkowski.unij.service.api)
and consists of the following interfaces:

-   `Collection` factories:
    [`UnmodifiableListFactory`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/collect/UnmodifiableListFactory.java),
    [`UnmodifiableSetFactory`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/collect/UnmodifiableSetFactory.java),
    [`UnmodifiableMapFactory`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/collect/UnmodifiableMapFactory.java)

-   miscellaneous:
    [`MiscellaneousApiProvider`](subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/misc/MiscellaneousApiProvider.java)

A module providing implementations of one or more of these interfaces constitutes a [binding](#bindings).

### API Specification

UniJ APIs come with a detailed specification for the [Service API](#service-api) interfaces. The specification is based
on the contract of the original JDK API (expressed mostly in JavaDoc), and tries to follow it as close as possible.

The focal points of the specification are:
-   `null` treatment (no `null`s allowed)
-   duplicate handling (e.g. no duplicates allowed in `of` methods of `UniSets` and `UniMaps`)
-   consistency (e.g. only one empty collection instance)

The specification is fully expressed as the following [Spock](http://spockframework.org/) test classes defined
[`pl.tlinkowski.unij.test`](subprojects/pl.tlinkowski.unij.test):

-   `Collection` factories:
    [`UnmodifiableListFactorySpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/collect/UnmodifiableListFactorySpec.groovy),
    [`UnmodifiableSetFactorySpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/collect/UnmodifiableSetFactorySpec.groovy),
    [`UnmodifiableMapFactorySpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/collect/UnmodifiableMapFactorySpec.groovy)

-   miscellaneous:
    [`MiscellaneousApiProviderSpec`](subprojects/pl.tlinkowski.unij.test/src/main/groovy/pl/tlinkowski/unij/test/service/misc/MiscellaneousApiProviderSpec.groovy)

Read the source of the Spock tests linked above to see what *every* UniJ binding guarantees.

## Bindings

A binding is simply a library with implementation(s) of the [Service API](#service-api).

Note that [UniJ supports multiple bindings of the same type at runtime](docs/Q-and-A.md#multiple-bindings-at-runtime). 

### Predefined Bindings

UniJ comes with a number of predefined bindings, which can all be found under
[`subprojects/bindings`](subprojects/bindings).

#### Collection Factory API Bindings

UniJ currently provides four types of `Collection` factory API bindings:

1.  **JDK 10** ([`pl.tlinkowski.unij.service.collect.jdk10`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk10))

    -   simply forwards all calls to the JDK

    -   example: [`Jdk10UnmodifiableListFactory`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk10/src/main/java/pl/tlinkowski/unij/service/collect/jdk10/Jdk10UnmodifiableListFactory.java)

2.  **JDK 8** ([`pl.tlinkowski.unij.service.collect.jdk8`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk8))

    -   provides regular mutable JDK 8 collections wrapped using
        [`Collections.unmodifiableList/Set/Map`](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableList-java.util.List-)

    -   example: [`Jdk8UnmodifiableListFactory`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk8/src/main/java/pl/tlinkowski/unij/service/collect/jdk8/Jdk8UnmodifiableListFactory.java)

3.  [**Guava**](https://github.com/google/guava) ([`pl.tlinkowski.unij.service.collect.guava`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.guava))

    -   provides Guava's [`ImmutableList`](https://guava.dev/releases/28.0-jre/api/docs/com/google/common/collect/ImmutableList.html)/[`ImmutableSet`](https://guava.dev/releases/28.0-jre/api/docs/com/google/common/collect/ImmutableSet.html)/[`ImmutableMap`](https://guava.dev/releases/28.0-jre/api/docs/com/google/common/collect/ImmutableMap.html)
        implementations

    -   example: [`GuavaUnmodifiableListFactory`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.guava/src/main/java/pl/tlinkowski/unij/service/collect/guava/GuavaUnmodifiableListFactory.java)

    -   note: Guava is a compile-only dependency for this binding
        (see [Guava / Eclipse Collections](docs/USAGE.md#guava--eclipse-collections) for details)

4.  [**Eclipse Collections**](https://www.eclipse.org/collections/) ([`pl.tlinkowski.unij.service.collect.eclipse`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.eclipse))

    -   provides Eclipse's [`ImmutableList`](https://www.eclipse.org/collections/javadoc/10.0.0/org/eclipse/collections/api/list/ImmutableList.html)/[`ImmutableSet`](https://www.eclipse.org/collections/javadoc/10.0.0/org/eclipse/collections/api/set/ImmutableSet.html)/[`ImmutableMap`](https://www.eclipse.org/collections/javadoc/10.0.0/org/eclipse/collections/api/map/ImmutableMap.html)
        implementations

    -   example: [`EclipseUnmodifiableListFactory`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.eclipse/src/main/java/pl/tlinkowski/unij/service/collect/eclipse/EclipseUnmodifiableListFactory.java)

    -   note: Eclipse Collections is a compile-only dependency for this binding
        (see [Guava / Eclipse Collections](docs/USAGE.md#guava--eclipse-collections) for details)

#### Miscellaneous API Bindings

UniJ currently provides two types of miscellaneous API bindings:

1.  **JDK 11** ([`pl.tlinkowski.unij.service.misc.jdk11`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11))

    -   simply forwards all calls to the JDK

    -   example: [`Jdk11MiscellaneousApiProvider`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11/src/main/java/pl/tlinkowski/unij/service/misc/jdk11/Jdk11MiscellaneousApiProvider.java)

2.  **JDK 8** ([`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8))

    -   provides custom implementations based on the ones in JDK 11

    -   example: [`Jdk8MiscellaneousApiProvider`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8/src/main/java/pl/tlinkowski/unij/service/misc/jdk8/Jdk8MiscellaneousApiProvider.java)

### Custom Bindings

Instead of any of the [predefined bindings](#predefined-bindings) mentioned above,
you can create and depend on a *custom binding*.

**See**:
-   [Usage for Custom Binding Providers](docs/USAGE.md#service-api-for-custom-binding-providers)
-   [Q&A for Custom Binding Providers](docs/Q-and-A.md#custom-service-provider-qa)

## Bundles

A UniJ bundle is a module having no source (save for its `module-info.java`) and depending on the following three modules:

1.  [`pl.tlinkowski.unij.api`](subprojects/api/pl.tlinkowski.unij.api) module (transitive dependency)
2.  `Collection` factory API binding (= one of `pl.tlinkowski.unij.service.collect.___` modules)
3.  miscellaneous API binding (= one of `pl.tlinkowski.unij.service.misc.___` modules)

### Predefined Bundles

Currently, UniJ provides the following four [bundles](subprojects/bundles):

1.  **JDK 11** bundle ([`pl.tlinkowski.unij.bundle.jdk11`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk11)), made of:
    -   [`pl.tlinkowski.unij.service.collect.jdk10`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk10)
    -   [`pl.tlinkowski.unij.service.misc.jdk11`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11)

2.  **pure JDK 8** bundle ([`pl.tlinkowski.unij.bundle.jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8)), made of:
    -   [`pl.tlinkowski.unij.service.collect.jdk8`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.jdk8)
    -   [`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)

3.  **Guava on JDK 8** bundle ([`pl.tlinkowski.unij.bundle.guava_jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.guava_jdk8)), made of:
    -   [`pl.tlinkowski.unij.service.collect.guava`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.guava)
    -   [`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)

4.  **Eclipse Collections on JDK 8** bundle ([`pl.tlinkowski.unij.bundle.eclipse_jdk8`](subprojects/bundles/pl.tlinkowski.unij.bundle.eclipse_jdk8)), made of:
    -   [`pl.tlinkowski.unij.service.collect.eclipse`](subprojects/bindings/collect/pl.tlinkowski.unij.service.collect.eclipse)
    -   [`pl.tlinkowski.unij.service.misc.jdk8`](subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8)

## Usage

See [Usage](docs/USAGE.md) document.

## Extra Information

See [Extra Information](docs/EXTRA.md) document.

## Questions & Answers

See [Q&A](docs/Q-and-A.md) document.

## Changelog

See [Changelog](CHANGELOG.md) document.

## Requirements

Usage: JDK 8+.

Building: Gradle 5+, JDK 11+.

## About the Author

See my webpage ([tlinkowski.pl](https://tlinkowski.pl/)) or
find me on Twitter ([@t_linkowski](https://twitter.com/t_linkowski)).
