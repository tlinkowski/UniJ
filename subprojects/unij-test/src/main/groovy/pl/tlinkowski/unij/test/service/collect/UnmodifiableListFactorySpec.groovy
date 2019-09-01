/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.tlinkowski.unij.test.service.collect

import static pl.tlinkowski.unij.test.service.collect.UnmodifiableCollectionSpecHelper.*
import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors

import pl.tlinkowski.unij.service.api.collect.UnmodifiableListFactory

/**
 * Specification for {@link UnmodifiableListFactory}.
 *
 * Inheriting classes should initialize {@code factory} in the {@code setupSpec} method.
 *
 * @author Tomasz Linkowski
 */
abstract class UnmodifiableListFactorySpec extends Specification {

  @Shared
  protected UnmodifiableListFactory factory

  def "proper service is registered on classpath"() {
    when:
      def registered = ServiceLoader.load(UnmodifiableListFactory).first()
    then:
      registered.class == factory.class
  }

  //region STANDARD CONTRACT
  def "collector"(List<Integer> list) {
    expect:
      collect(factory.collector(), list) == collect(Collectors.toUnmodifiableList(), list)
    where:
      list << lists()
  }

  def "copyOf"(List<Integer> list) {
    expect:
      factory.copyOf(list) == List.copyOf(list)
    where:
      list << lists()
  }

  def "of(n=0)"() {
    expect:
      factory.of() == List.of()
  }

  def "of(n=1)"() {
    expect:
      factory.of(1) == List.of(1)
  }

  def "of(n=2)"() {
    expect:
      factory.of(1, 2) == List.of(1, 2)
  }

  def "of(n=3)"() {
    expect:
      factory.of(1, 2, 3) == List.of(1, 2, 3)
  }

  def "of(n=4)"() {
    expect:
      factory.of(1, 2, 3, 4) == List.of(1, 2, 3, 4)
  }

  def "of(n=5)"() {
    expect:
      factory.of(1, 2, 3, 4, 5) == List.of(1, 2, 3, 4, 5)
  }

  def "of(n=6)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6) ==
              List.of(1, 2, 3, 4, 5, 6)
  }

  def "of(n=7)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7) ==
              List.of(1, 2, 3, 4, 5, 6, 7)
  }

  def "of(n=8)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8) ==
              List.of(1, 2, 3, 4, 5, 6, 7, 8)
  }

  def "of(n=9)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9) ==
              List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  def "of(n=10)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) ==
              List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

  def "of(...)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11) ==
              List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
  }
  //endregion

  //region NULLABILITY CONTRACT
  def "collector throws NPE"(List<Integer> list) {
    when:
      list.stream().collect(factory.collector())
    then:
      thrown(NullPointerException)
    where:
      list << listsWithNull()
  }

  def "copyOf throws NPE"(List<Integer> list) {
    when:
      factory.copyOf(list)
    then:
      thrown(NullPointerException)
    where:
      list << listsWithNull()
  }

  def "of(...) throws NPE"(List<Integer> list) {
    given:
      Integer[] array = list
    when:
      factory.of(array)
    then:
      thrown(NullPointerException)
    where:
      list << listsWithNull()
  }

  def "of(n=1) throws NPE"() {
    when:
      factory.of(null)
    then:
      thrown(NullPointerException)
  }

  def "of(n=2) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(2, nullIndex)
    when:
      factory.of(a[0], a[1])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..1)
  }

  def "of(n=3) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(3, nullIndex)
    when:
      factory.of(a[0], a[1], a[2])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..2)
  }

  def "of(n=4) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(4, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..3)
  }

  def "of(n=5) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(5, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3], a[4])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..4)
  }

  def "of(n=6) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(6, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3], a[4], a[5])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..5)
  }

  def "of(n=7) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(7, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3], a[4], a[5], a[6])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..6)
  }

  def "of(n=8) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(8, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..7)
  }

  def "of(n=9) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(9, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..8)
  }

  def "of(n=10) throws NPE"(int nullIndex) {
    given:
      def a = argsWithNull(10, nullIndex)
    when:
      factory.of(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9])
    then:
      thrown(NullPointerException)
    where:
      nullIndex << (0..9)
  }
  //endregion

  //region CONSISTENCY CONTRACT
  def "of(n=0) has only one instance"(List<Integer> list) {
    when:
      def actual = factory.of()
    then:
      list.is(actual)
    where:
      list            | _
      factory.of()    | _
      ofSized(0)      | _
      copyOfSized(0)  | _
      collectSized(0) | _
  }

  def "of(n=1) has only one type"(List<Integer> list) {
    when:
      def actual = factory.of(1)
    then:
      list == actual
      list.class == actual.class
    where:
      list            | _
      factory.of(1)   | _
      ofSized(1)      | _
      copyOfSized(1)  | _
      collectSized(1) | _
  }

  def "of(n=2) has only one type"(List<Integer> list) {
    when:
      def actual = factory.of(1, 2)
    then:
      list == actual
      list.class == actual.class
    where:
      list             | _
      factory.of(1, 2) | _
      ofSized(2)       | _
      copyOfSized(2)   | _
      collectSized(2)  | _
  }

  def "of(n=3) has only one type"(List<Integer> list) {
    when:
      def actual = factory.of(1, 2, 3)
    then:
      list == actual
      list.class == actual.class
    where:
      list                | _
      factory.of(1, 2, 3) | _
      ofSized(3)          | _
      copyOfSized(3)      | _
      collectSized(3)     | _
  }

  def "of(n=10) has only one type"(List<Integer> list) {
    when:
      def actual = factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    then:
      list == actual
      list.class == actual.class
    where:
      list                                      | _
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) | _
      ofSized(10)                               | _
      copyOfSized(10)                           | _
      collectSized(10)                          | _
  }

  def "of(n=11) has only one type"(List<Integer> list) {
    when:
      def actual = factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    then:
      list == actual
      list.class == actual.class
    where:
      list                                          | _
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11) | _
      ofSized(11)                                   | _
      copyOfSized(11)                               | _
      collectSized(11)                              | _
  }

  private List<Integer> ofSized(int size) {
    factory.of((Integer[]) args(size))
  }

  private List<Integer> copyOfSized(int size) {
    factory.copyOf(args(size))
  }

  private List<Integer> collectSized(int size) {
    collect(factory.collector(), args(size))
  }
  //endregion
}
