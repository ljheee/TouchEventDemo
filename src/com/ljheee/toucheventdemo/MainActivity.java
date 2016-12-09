package com.ljheee.toucheventdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	private TextView touchView;//触屏测试区
	private TextView showView;//数据显示区

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        touchView = (TextView) findViewById(R.id.touch_area);
        showView = (TextView) findViewById(R.id.showView);
        
        touchView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					display("触摸事件", event);
					break;
				case MotionEvent.ACTION_MOVE:
					display("移动事件", event);
					break;
				case MotionEvent.ACTION_UP:
					display("触摸离开", event);
					break;

				default:
					break;
				}
				
				return false;
			}
		});
    }

    /**
     * 显示触屏测试结果
     * @param type
     * @param event
     */
    public void display(String type, MotionEvent event){
    	float x = event.getX();
		float y = event.getY();
		float pressure = event.getPressure();
		float size = event.getSize();
		float rawX = event.getRawX();
		float rawY = event.getRawY();			
		String msg = "事件类型："+type+"  历史数据:"+ProcessHistory(event)+"\n";
		msg += "相对坐标："+x+","+y+"\n";
		msg += "绝对坐标："+rawX+","+rawY+"\n";
		msg += "触点压力："+pressure+"，  ";
		msg += "触点尺寸："+size+"\n";
		    	
		showView.setText(msg);
    }
    
    /**
     * 处理历史数据
     * @param event
     * @return
     */
    private int ProcessHistory(MotionEvent event){
    	//获取历史记录点
    	int historySize = event.getHistorySize();
    	
    	for (int i = 0; i < historySize; i++) {//循环获得各个点数据
			long time = event.getHistoricalEventTime(i);
			float  pressure = event.getHistoricalPressure(i);
			float x = event.getHistoricalX(i);
			float y = event.getHistoricalY(i);
			float size = event.getHistoricalSize(i);
			
			//其他处理
		}
		return historySize;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * 选项菜单--点击事件处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
		case R.id.action_exit:
			finish();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
}
