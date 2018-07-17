package com.cmm.zjwz.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by cmm on 17/7/22.
 */
public interface OnTextChange {
    public void addTextChangedListener(TextWatcher watcher);

    public boolean isEmpty();

    public Editable getText();
}
