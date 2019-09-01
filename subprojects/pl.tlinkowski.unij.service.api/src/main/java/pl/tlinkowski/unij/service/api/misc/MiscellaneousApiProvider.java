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
package pl.tlinkowski.unij.service.api.misc;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

/**
 * Provider of miscellaneous JDK APIs added since JDK 9. Provides:
 * <ul>
 *   <li>a few {@link Collector}s from {@link Collectors} utility class</li>
 * </ul>
 *
 * @author Tomasz Linkowski
 */
public interface MiscellaneousApiProvider {

  //region COLLECTORS

  /**
   * Equivalent of {@link Collectors#flatMapping(Function, Collector)}.
   */
  <T, U, A, R> Collector<T, ?, R> flatMappingCollector(Function<? super T, ? extends Stream<? extends U>> mapper,
          Collector<? super U, A, R> downstream);

  /**
   * Equivalent of {@link Collectors#filtering(Predicate, Collector)}.
   */
  <T, A, R> Collector<T, ?, R> filteringCollector(Predicate<? super T> predicate,
          Collector<? super T, A, R> downstream);
  //endregion
}
