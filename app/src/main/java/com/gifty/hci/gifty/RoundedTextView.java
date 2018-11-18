package com.gifty.hci.gifty;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class RoundedTextView extends AppCompatTextView {
    private boolean topLeft;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomRight;

    private float cornerRadius;

    public RoundedTextView(Context context){
        super(context);
    }

    public RoundedTextView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    public RoundedTextView(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = this.getPath();
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    private Path getPath(){
        Path path = new Path();
        float[] radii = new float[8];

        if (this.topLeft) {
            radii[0] = cornerRadius;
            radii[1] = cornerRadius;
        }

        if (this.topRight) {
            radii[2] = cornerRadius;
            radii[3] = cornerRadius;
        }

        if (this.bottomRight) {
            radii[4] = cornerRadius;
            radii[5] = cornerRadius;
        }

        if (this.bottomLeft) {
            radii[6] = cornerRadius;
            radii[7] = cornerRadius;
        }
        path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), radii, Path.Direction.CW);
        return path;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setTopLeft(boolean topLeft) {
        this.topLeft = topLeft;
    }

    public void setTopRight(boolean topRight) {
        this.topRight = topRight;
    }

    public void setBottomLeft(boolean bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public void setBottomRight(boolean bottomRight) {
        this.bottomRight = bottomRight;
    }
}
