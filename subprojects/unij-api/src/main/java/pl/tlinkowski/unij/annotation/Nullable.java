/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018 Tomasz Linkowski.
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
package pl.tlinkowski.unij.annotation;

import java.lang.annotation.*;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.When;

/**
 * Declares that the annotated return value, parameter, field, or local variable is nullable.
 *
 * @author Tomasz Linkowski
 */
// https://youtrack.jetbrains.com/issue/KT-10942
@Documented
@Nonnull(when = When.MAYBE)
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE,
        ElementType.TYPE_PARAMETER, ElementType.TYPE_USE
})
@TypeQualifierNickname
public @interface Nullable {

}
