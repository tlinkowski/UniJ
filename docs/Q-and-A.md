# UniJ: Questions & Answers

← back to [UniJ README](../README.md)

Table of Contents:

-   [Generic Q&A](#generic-qa)
-   [End User Q&A](#end-user-qa)
-   [Library Provider Q&A](#library-provider-qa)
-   [Custom Service Provider Q&A](#custom-service-provider-qa)

## Generic Q&A

### Two APIs

**Q**:
Why are there two APIs ([User API](../README.md#user-api) and [Service API](../README.md#service-api)) in UniJ?

**A**:
So that:

1.  for the **user**, we can exactly mirror the JDK API (equivalent class names, static methods), e.g.:
    -   `List.of()` ⟷ `UniLists.of()`
    -   `Collectors.toUnmodifiableList()` ⟷ `UniCollectors.toUnmodifiableList()`

2.  for the **service** provider, we can expose an interface with all the related method to be implemented *together*, e.g.:
    -   `UnmodifiableListFactory.of()`
    -   `UnmodifiableListFactory.collector()`

### Multiple Bindings at Runtime

**Q**:
What happens if multiple bindings of the same type are available at runtime
(e.g. `pl.tlinkowski.unij.service.misc.jdk8` and `pl.tlinkowski.unij.service.misc.jdk11`)?

**A**:
If multiple bindings of the same type are present on the runtime classpath/modulepath, service implementations with the top
[priority](../subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/UniJService.java)
(lowest number) will be selected.

In the example above,
[`Jdk11MiscellaneousApiProvider`](../subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk11/src/main/java/pl/tlinkowski/unij/service/misc/jdk11/Jdk11MiscellaneousApiProvider.java)
(priority = 10) will be selected over
[`Jdk8MiscellaneousApiProvider`](../subprojects/bindings/misc/pl.tlinkowski.unij.service.misc.jdk8/src/main/java/pl/tlinkowski/unij/service/misc/jdk8/Jdk8MiscellaneousApiProvider.java)
(priority = 40) for
[`MiscellaneousApiProvider`](../subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/misc/MiscellaneousApiProvider.java).

## End User Q&A

### `ArrayList` + `Collections.unmodifiableList` for End User

**Q**:
Why not simply use [`ArrayList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)s
wrapped with
[`Collections.unmodifiableList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html#unmodifiableList(java.util.List)),
etc. instead of UniJ?

**A**:
You can do it if you find it convenient. To me, it's too verbose (low readability), and hence more error-prone.
Besides, it's not [null-safe](../README.md#api-specification), not as efficient (UniJ's JDK 8 binding uses
`Arrays.asList` instead of `ArrayList`), and doesn't allow for easy migration once you upgrade to JDK 11+.

### Guava / Eclipse Collections for End User

**Q**:
Why not depend on an external library providing immutable `List`s instead of on UniJ (e.g.
[Guava](https://github.com/google/guava) or [Eclipse Collections](https://www.eclipse.org/collections/))?
    
**A**:
If you program to Guava's / Eclipse Colletions' proprietary APIs, you get rather far from the JDK 9+ API and
its [specification](../README.md#api-specification). Once you decide to upgrade to JDK 11+, migrating to JDK's
collections will **not** be straightforward due to API differences.

### UniJ Dependency on JDK 11+

**Q**:
What to do about a dependency on UniJ once I migrate to JDK 11+?

**A**:
In such case, it's best to remove UniJ altogether (will be a simple matter of replacing all occurrences of `UniLists`
with `List`, etc.). Alternatively (if you don't have time for that), you can just change your
[UniJ bundle](../README.md#bundles) to
[`pl.tlinkowski.unij.bundle.jdk11`](../subprojects/bundles/pl.tlinkowski.unij.bundle.jdk11).

## Library Provider Q&A

### `ArrayList` + `Collections.unmodifiableList` for a Library

**Q**:
Why not simply use [`ArrayList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)s
wrapped with
[`Collections.unmodifiableList`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Collections.html#unmodifiableList(java.util.List)),
etc. instead of UniJ?
     
**A**:
See [this end-user Q&A](#arraylist--collectionsunmodifiablelist-for-end-user). Moreover, if your users themselves use JDK 9's,
Guava's or Eclipse's `Collection`s:

-   you'd waste potential by not using the best `Collection` implementations available
-   you'd introduce inconsistency regarding which `Collection` implementations are used in your users' app/library

### Guava / Eclipse Collections for a Library

**Q**:
Why not depend on an external library providing immutable `List`s instead of on UniJ (e.g.
[Guava](https://github.com/google/guava) or [Eclipse Collections](https://www.eclipse.org/collections/))?

**A**:
See [this end-user Q&A](#guava--eclipse-collections-for-end-user). Moreover, by bundling with an external library, you'd
impose a *heavy* (potentially unwanted) dependency on your users. On the other hand, bundling with extremely
lightweight UniJ API shouldn't cause any problems.

### Why Facade for a Library

**Q**:
Why bother with a `Collection` factory method facade (like UniJ) for a library?

**A**:
As a library maintainer, the *choice* of `Collection` implementations *shouldn't be yours*. It's the same as with
logging — you shouldn't choose the logging *backend*, and should only program to a **facade** like
[SLF4J](https://www.slf4j.org/). That's *precisely* what UniJ lets you do with respect to `Collection` factory methods.

### How to Use UniJ in a Library

**Q**:
How can I use UniJ in my library?

**A**:
You can choose one of the below artifacts as a dependency for your library:

1.   *(recommended)* UniJ User API ([`pl.tlinkowski.unij.api`](../subprojects/api/pl.tlinkowski.unij.api))

     -   pros: extremely lightweight (API only); no redundant dependencies

     -   cons: your users **must** add a dependency on a UniJ bundle / bindings of their choosing
     
2.   *(alternative)* UniJ JDK 8 bundle ([`pl.tlinkowski.unij.bundle.jdk8`](../subprojects/bundles/pl.tlinkowski.unij.bundle.jdk8))

     -   pros: your users **don't have to** add a dependency on any UniJ bundle / bindings
         (they **may** do so to [override](#multiple-bindings-at-runtime) the JDK 8 bindings, though)

     -   cons: heavier than API only; may result in redundant dependencies if the user overrides some bindings

Here's what needs to be done by *you* and *your users* to use each option:

1.  UniJ User API:
    -   *you* follow [Usage for Library Providers](USAGE.md#user-api-for-library-providers)
    -   you ask *your users* to follow [Usage for End Users](USAGE.md#implementation-for-end-users)

2.  UniJ JDK 8 bundle:
    -   *you* follow [Basic Implementation Usage: Bundles](USAGE.md#basic-implementation-usage-bundles)
    -   you inform *your users* they *may* follow [Usage for End Users](USAGE.md#implementation-for-end-users)

In both cases, *your users* may need to add certain [external dependencies](USAGE.md#external-dependencies-for-end-users).

## Custom Service Provider Q&A

### How to Provide a Custom UniJ Binding

**Q**:
How can I create a custom UniJ service implementation and provide it as a binding?

**A**:
You can create a custom UniJ binding by:

-   adding proper dependencies to your project
    (see [Usage for Custom Binding Providers](USAGE.md#service-api-for-custom-binding-providers))

-   implementing an interface from the [Service API](../README.md#service-api)

-   annotating it with a special [`@UniJService`](../subprojects/api/pl.tlinkowski.unij.service.api/src/main/java/pl/tlinkowski/unij/service/api/UniJService.java)
    annotation

-   providing a `module-info.java` entry (for JDK 9+) and/or a `META-INF` JAR entry (for JDK 8; I recommend Google's
    [`@AutoService`](https://github.com/google/auto/tree/master/service) for it)

Example:

```java
@UniJService(priority = 1)
@AutoService(UnmodifiableListFactory.class)
public class CustomUnmodifiableListFactory implements UnmodifiableListFactory {
  // ...
}
```

You should also create the following Spock specification to ensure that your service implementation adheres to the
[UniJ specification](../README.md#api-specification):

```groovy
class CustomUnmodifiableListFactorySpec extends UnmodifiableListFactorySpec {

  def setupSpec() {
    factory = new CustomUnmodifiableListFactory()
  }
}
```

---

← back to [UniJ README](../README.md)
