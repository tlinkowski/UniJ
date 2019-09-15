# Releases

## Version 0.1.0

Release date: 15/09/2019.

### Enhancements

-   [#51](https://github.com/tlinkowski/UniJ/issues/51): Add sample projects
-   [#50](https://github.com/tlinkowski/UniJ/issues/50): Update `pl.tlinkowski.annotation.basic` to 0.2.0
-   [#49](https://github.com/tlinkowski/UniJ/issues/49): Update `pl.tlinkowski.gradle.my.superpom` to 0.3.0
-   [#48](https://github.com/tlinkowski/UniJ/issues/48): Change artifact names to module names
-   [#47](https://github.com/tlinkowski/UniJ/issues/47): [unij-test] Tighten the Spock specs
-   [#43](https://github.com/tlinkowski/UniJ/issues/43): Enable Bintray for all subprojects
-   [#41](https://github.com/tlinkowski/UniJ/issues/41): Improve `unij-service-api` project to export everything under `pl.tlinkowski.unij.service.api` package
-   [#39](https://github.com/tlinkowski/UniJ/issues/39): Extract common `unij-bundle-*` and `unij-(collect|misc)-*` configurations to `build.gradle.kts`
-   [#37](https://github.com/tlinkowski/UniJ/issues/37): Apply java-library plugin in all subprojects
-   [#36](https://github.com/tlinkowski/UniJ/issues/36): Extract unij-service-api project
-   [#35](https://github.com/tlinkowski/UniJ/issues/35): Add dependency on basic-annotations
-   [#34](https://github.com/tlinkowski/UniJ/issues/34): Use Google's @AutoService for generating META-INF/services
-   [#33](https://github.com/tlinkowski/UniJ/issues/33): Apply Kordamp settings plugin
-   [#32](https://github.com/tlinkowski/UniJ/issues/32): Introduce "bundle" modules
-   [#31](https://github.com/tlinkowski/UniJ/issues/31): Architecture improvements
-   [#30](https://github.com/tlinkowski/UniJ/issues/30): Support logging facade & log which services get loaded by UniJ
-   [#29](https://github.com/tlinkowski/UniJ/issues/29): Configure code quality reporting
-   [#28](https://github.com/tlinkowski/UniJ/issues/28): Implement unij-misc-jdk8 module
-   [#27](https://github.com/tlinkowski/UniJ/issues/27): Implement unij-collect-jdk8 module
-   [#26](https://github.com/tlinkowski/UniJ/issues/26): Implement unij-collect-eclipse module
-   [#25](https://github.com/tlinkowski/UniJ/issues/25): Implement unij-collect-guava module
-   [#24](https://github.com/tlinkowski/UniJ/issues/24): Create MiscellaneousApiProvider and related classes
-   [#23](https://github.com/tlinkowski/UniJ/issues/23): Create UnmodifiableMapFactory and related classes
-   [#22](https://github.com/tlinkowski/UniJ/issues/22): Create UnmodifiableSetFactory and related classes
-   [#21](https://github.com/tlinkowski/UniJ/issues/21): Support loading priority for UniJ services
-   [#20](https://github.com/tlinkowski/UniJ/issues/20): Configure Javadoc generation to link external Javadocs
-   [#18](https://github.com/tlinkowski/UniJ/issues/18): Create UniJ service loading class
-   [#17](https://github.com/tlinkowski/UniJ/issues/17): Create public static UniLists and UniCollectors
-   [#16](https://github.com/tlinkowski/UniJ/issues/16): Create UnmodifiableListFactory and related classes
-   [#14](https://github.com/tlinkowski/UniJ/issues/14): Add a Gradle plugin for project details
-   [#13](https://github.com/tlinkowski/UniJ/issues/13): Create a JMH benchmark for direct calls vs UniJ calls
-   [#12](https://github.com/tlinkowski/UniJ/issues/12): Introduce JPMS modules
-   [#11](https://github.com/tlinkowski/UniJ/issues/11): Update README.md
-   [#10](https://github.com/tlinkowski/UniJ/issues/10): Configure Travis CI
-   [#9](https://github.com/tlinkowski/UniJ/issues/9): Add IntelliJ configuration
-   [#7](https://github.com/tlinkowski/UniJ/issues/7): Configure code coverage reporting
-   [#6](https://github.com/tlinkowski/UniJ/issues/6): Configure publishing in Gradle
-   [#5](https://github.com/tlinkowski/UniJ/issues/5): Configure releasing in Gradle
-   [#3](https://github.com/tlinkowski/UniJ/issues/3): Update Gradle to 5.2.1
-   [#1](https://github.com/tlinkowski/UniJ/issues/1): Add initial Gradle configuration

### Bug Fixes

-   [#42](https://github.com/tlinkowski/UniJ/issues/42): Fix `module-info.java` for `unij-bundle-*`
-   [#38](https://github.com/tlinkowski/UniJ/issues/38): The code being documented uses modules but the packages defined in [...] are in the unnamed module
