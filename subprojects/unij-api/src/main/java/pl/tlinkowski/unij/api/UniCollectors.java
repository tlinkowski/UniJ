/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019 Tomasz Linkowski.
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
package pl.tlinkowski.unij.api;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

import lombok.experimental.UtilityClass;

/**
 * Provides some {@link Collector} factory methods present in {@link java.util.stream.Collectors} class.
 *
 * @author Tomasz Linkowski
 */
@UtilityClass
public final class UniCollectors {

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableList()}.
   */
  public static <T> Collector<T, ?, /*@ReadOnly*/ List<T>> toUnmodifiableList() {
    return UniJ.listFactory().collector();
  }

  /**
   * Equivalent of {@link java.util.stream.Collectors#toUnmodifiableSet()}.
   */
  public static <T> Collector<T, ?, /*@ReadOnly*/ Set<T>> toUnmodifiableSet() {
    return UniJ.setFactory().collector();
  }
}
