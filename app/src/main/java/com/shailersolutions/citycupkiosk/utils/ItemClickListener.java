package com.shailersolutions.citycupkiosk.utils;

import android.view.View;

public interface ItemClickListener {
    void OnItemClick(View view, int position, int extra);
    void isFavorite(View view, int position,int favorite);
    void isDelete(View view, int position);
}
