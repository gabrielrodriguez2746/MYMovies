package com.mymovies.base.providers;

import androidx.annotation.StringRes;

public interface ResourceProvider {

    String getString(@StringRes int id);
}
