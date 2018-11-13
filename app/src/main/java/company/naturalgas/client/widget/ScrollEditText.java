package company.naturalgas.client.widget;

import android.widget.EditText;
import android.content.Context;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatEditText;

public class ScrollEditText extends AppCompatEditText
{
    public ScrollEditText(Context context)
    {
        super(context);
    }

    public ScrollEditText(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ScrollEditText(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        final int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_MOVE)
        {
            if (canVerticalScroll(this))
            {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            else
            {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        else if (action == MotionEvent.ACTION_UP)
        {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.onTouchEvent(event);
    }

    /**
     * EditText竖直方向是否可以滚动
     * @param editText  需要判断的EditText
     * @return  true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText)
    {
        int scrollY = editText.getScrollY();
        int scrollRange = editText.getLayout().getHeight();
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() -editText.getCompoundPaddingBottom();
        int scrollDifference = scrollRange - scrollExtent;
        if(scrollDifference == 0) return false;
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }
}