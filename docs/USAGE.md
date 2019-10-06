# UniJ: Usage

← back to [UniJ README](../README.md)

Table of Contents:

-   [Implementation (for End Users)](#implementation-for-end-users)
-   [User API (for Library Providers)](#user-api-for-library-providers)
-   [Service API (for Custom Binding Providers)](#service-api-for-custom-binding-providers)

*Note*: UniJ resides in [Maven Central](https://search.maven.org/search?q=g:pl.tlinkowski.unij).

## Implementation (for End Users)

An end user can provide UniJ implementation in two ways:

-   [basic](#basic-implementation-usage-bundles):
    through a *single* dependency on a [UniJ bundle](../README.md#bundles)

-   [complex](#complex-implementation-usage-api--bindings): through *three* dependencies:
    1.  on the [User API](../README.md#user-api) (**optional** if already provided by a [library](#user-api-for-library-providers))
    2.  on a [`Collection` factory API binding](../README.md#collection-factory-api-bindings)
    3.  on a [miscellaneous API binding](../README.md#miscellaneous-api-bindings)

In all cases, the end user may also need to add certain [external dependencies](#external-dependencies-for-end-users).

### Basic Implementation Usage (Bundles)

<details open>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
implementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.bundle.{B}", version = "x.y.z")
```

</details>

<details>
<summary>Gradle (Groovy DSL)</summary>

```groovy
implementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.bundle.{B}', version: 'x.y.z'
```

</details>

<details>
<summary>Maven</summary>

```xml
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.bundle.{B}</artifactId>
  <version>x.y.z</version>
</dependency>
```

</details>

<details open>
<summary>JPMS (<code>module-info.java</code>)</summary>

```java
requires pl.tlinkowski.unij.bundle.{B};
```

</details>

where `{B}` can be one of: `jdk8`, `jdk11`, `guava_jdk8`, or `eclipse_jdk8`
(see [Bundles](../README.md#bundles) for details). 

### Complex Implementation Usage (API + Bindings)

<details open>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
implementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.api", version = "x.y.z")
implementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.service.collect.{C}", version = "x.y.z")
implementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.service.misc.{M}", version = "x.y.z")
```

</details>

<details>
<summary>Gradle (Groovy DSL)</summary>

```groovy
implementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.api', version: 'x.y.z'
implementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.service.collect.{C}', version: 'x.y.z'
implementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.service.misc.{M}', version: 'x.y.z'
```

</details>

<details>
<summary>Maven</summary>

```xml
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.api</artifactId>
  <version>x.y.z</version>
</dependency>
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.service.collect.{C}</artifactId>
  <version>x.y.z</version>
</dependency>
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.service.misc.{M}</artifactId>
  <version>x.y.z</version>
</dependency>
```

</details>

<details open>
<summary>JPMS (<code>module-info.java</code>)</summary>

```java
requires pl.tlinkowski.unij.api;
requires pl.tlinkowski.unij.service.collect.{C};
requires pl.tlinkowski.unij.service.misc.{M};
```

</details>

where:

-   `{C}` can be one of: `jdk8`, `jdk10`, `guava`, or `eclipse`
    (see [`Collection` Factory API Bindings](../README.md#collection-factory-api-bindings) for details)

-   `{M}` can be either `jdk8` or `jdk11`
    (see [Miscellaneous API Bindings](../README.md#miscellaneous-api-bindings) for details)

Note that, instead of any of the predefined bindings mentioned above,
you can create and depend on a [custom binding](../README.md#custom-bindings).

### External Dependencies (for End Users)

#### SLF4J

UniJ [User API](../README.md#user-api) has an `implementation` dependency on [SLF4J](https://www.slf4j.org/) API to
let you have insight into which implementation it chooses for each of its [Service API](../README.md#service-api)
interfaces.

As a result, if you use UniJ as an end user, you should also add a `runtimeOnly` dependency on one of
[SLF4J bindings](https://www.slf4j.org/manual.html#swapping). Otherwise, you'll see the following message at runtime:

> SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
>
> SLF4J: Defaulting to no-operation (NOP) logger implementation
>
> SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

#### Guava / Eclipse Collections

The dependencies on [Guava](https://github.com/google/guava) and
[Eclipse Collections](https://www.eclipse.org/collections/) (present only in modules with `guava` and `eclipse` in
their name, respectively) are `compileOnly` dependencies.

Thanks to this, dependency on UniJ won't affect the version of Guava / Eclipse Collections you want to use, since
you have to declare this dependency explicitly.

Depending on your use case, do the following:

-   if you want to use Guava / Eclipse Collections *implicitly* (= access to `Collection` implementations through UniJ only):
    -   add a `runtimeOnly` dependency on Guava / Eclipse Collections

-   if you want to use Guava / Eclipse Collections *explicitly* (= access to the entire API of the library):
    -   *(non-transitive)* add an `implementation` dependency + `requires` entry to `module-info.java`
    -   *(transitive)* add an `api` dependency + `requires transitive` entry to `module-info.java`

Minimal supported versions are:
-   Guava: [23.2](https://github.com/google/guava/releases/tag/v23.2)
-   Eclipse Collections: [9.0.0](https://github.com/eclipse/eclipse-collections/releases/tag/9.0.0)

## User API (for Library Providers)

A library provider can use UniJ [User API](../README.md#user-api) in two ways:

-   [non-transitive](#non-transitive-usage-of-user-api): so that only the library provider can program to
    the User API (the end user needs to be instructed to choose the UniJ implementation as per
    [Implementation Usage](#implementation-for-end-users))

-   [transitive](#transitive-usage-of-user-api): so that both the library provider and the end user can program to
    the User API (the end user still needs to be instructed to choose the UniJ implementation as per
    [Implementation Usage](#implementation-for-end-users))

### Non-Transitive Usage of User API

<details open>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
implementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.api", version = "x.y.z")
```

</details>

<details>
<summary>Gradle (Groovy DSL)</summary>

```groovy
implementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.api', version: 'x.y.z'
```

</details>

<details open>
<summary>JPMS (<code>module-info.java</code>)</summary>

```java
requires pl.tlinkowski.unij.api;
```

</details>

### Transitive Usage of User API

<details open>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
api(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.api", version = "x.y.z")
```

</details>

<details>
<summary>Gradle (Groovy DSL)</summary>

```groovy
api group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.api', version: 'x.y.z'
```

</details>

<details>
<summary>Maven</summary>

```xml
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.api</artifactId>
  <version>x.y.z</version>
</dependency>
```

</details>

<details open>
<summary>JPMS (<code>module-info.java</code>)</summary>

```java
requires transitive pl.tlinkowski.unij.api;
```

</details>

## Service API (for Custom Binding Providers)

UniJ [Service API](../README.md#service-api) should be used only in the rare case that you need to create
a [custom UniJ binding](../README.md#custom-bindings).

<details open>
<summary>Gradle (Kotlin DSL)</summary>

```kotlin
implementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.service.api", version = "x.y.z")
testImplementation(group = "pl.tlinkowski.unij", name = "pl.tlinkowski.unij.test", version = "x.y.z")
```

</details>

<details>
<summary>Gradle (Groovy DSL)</summary>

```groovy
implementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.service.api', version: 'x.y.z'
testImplementation group: 'pl.tlinkowski.unij', name: 'pl.tlinkowski.unij.test', version: 'x.y.z'
```

</details>

<details>
<summary>Maven</summary>

```xml
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.service.api</artifactId>
  <version>x.y.z</version>
</dependency>
<dependency>
  <groupId>pl.tlinkowski.unij</groupId>
  <artifactId>pl.tlinkowski.unij.test</artifactId>
  <version>x.y.z</version>
  <scope>test</scope>
</dependency>
```

</details>

<details open>
<summary>JPMS (<code>module-info.java</code>)</summary>

```java
requires pl.tlinkowski.unij.service.api;
```

</details>

---

← back to [UniJ README](../README.md)
