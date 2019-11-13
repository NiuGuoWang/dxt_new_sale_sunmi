package com.dxt.retail4sunmi.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MyDialog extends Dialog {
	    private View dialogView;

	    private MyDialog(Context context, int theme){
	        super(context, theme);
	    }
	    

	    
	    public static MyDialog createDialog(Context context, int theme,int layoutID,float height,float width,WindowManager m){
	    	MyDialog dialog = new MyDialog(context,theme);
	    	LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View dialogView = li.inflate(layoutID, null);
			dialog.setDialogView(dialogView);
			// 设置对话框布局
			dialog.setContentView(dialogView);
			Window dialogWindow = dialog.getWindow();
			dialogWindow.setGravity(Gravity.CENTER);
			Display d = m.getDefaultDisplay();
			WindowManager.LayoutParams p = dialogWindow.getAttributes();
			DisplayMetrics dm = new DisplayMetrics();
			d.getMetrics(dm);
			p.height = (int) (dm.heightPixels * height);
			p.width = (int) (dm.widthPixels * width);
			dialogWindow.setAttributes(p);
	    	return dialog;
	    }
	public View getDialogView() {
			return dialogView;
		}
		private void setDialogView(View dialogView) {
			this.dialogView = dialogView;
		}
	    
}
