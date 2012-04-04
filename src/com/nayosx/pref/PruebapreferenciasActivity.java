package com.nayosx.pref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class PruebapreferenciasActivity extends Activity implements OnClickListener, RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener{
    /** Called when the activity is first created. */
	SharedPreferences getPref ;
	SharedPreferences.Editor prefSave;
	Button bn;
	CheckBox ch;
	RadioButton r1, r2;
	RadioGroup rg;
	TextView tv;
	boolean estadoBoton, estadoR1, estadoR2;
	final String ESTBOTON="el estado del boton", ESTR1 = "radio 1", ESTR2 = "radio 2", PREFERENCIAS = "mis_preferences";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        inicializar();
    }
    private void inicializar()
    {
    	getPref = this.getSharedPreferences(PREFERENCIAS, Context.MODE_PRIVATE);
	    estadoBoton = getPref.getBoolean(ESTBOTON, estadoBoton);
	    estadoR1 = getPref.getBoolean(ESTR1, estadoR1);
	    estadoR2 = getPref.getBoolean(ESTR2, estadoR2);
    	
    	bn = (Button) findViewById(R.id.button1);
    	ch = (CheckBox) findViewById(R.id.checkBox1);
    	r1 = (RadioButton) findViewById(R.id.radioButton1);
    	r2 = (RadioButton) findViewById(R.id.radioButton2);
    	rg = (RadioGroup) findViewById(R.id.rg);
    	tv = (TextView) findViewById(R.id.textView4);
    	
    	bn.setOnClickListener(this);
    	
    	ch.setOnCheckedChangeListener(this);
    	ch.setChecked(estadoBoton);
    	rg.setOnCheckedChangeListener(this);
    	r1.setChecked(estadoR1);
    	r2.setChecked(estadoR2);
    	
    }
	@Override
	
    protected void onDestroy() {
	    // TODO Auto-generated method stub
	    super.onDestroy();
	    prefSave = getPref.edit();
	    prefSave.putBoolean(ESTBOTON, estadoBoton);
	    prefSave.putBoolean(ESTR1, estadoR1);
	    prefSave.putBoolean(ESTR2, estadoR2);
	    prefSave.commit();
    }

	/* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View p_arg0) {
	    // TODO Auto-generated method stub
	    mensaje("El boton esta activado, de lo contrario no saldria este mensaje");
    }
	/* (non-Javadoc)
     * @see android.widget.RadioGroup.OnCheckedChangeListener#onCheckedChanged(android.widget.RadioGroup, int)
     */
    @Override
    public void onCheckedChanged(RadioGroup p_group, int p_checkedId) {
	    // TODO Auto-generated method stub
    	if(r1.isChecked())
    	{
    		tv.setTextColor(Color.RED);
    		estadoR1 = true;
    		estadoR2 = false;
    	}
    	else if(r2.isChecked())
    	{
    		tv.setTextColor(Color.GREEN);
    		estadoR1 = false;
    		estadoR2 = true;
    	}
    }
	/**
     * @param p_string
     */
    private void mensaje(String p_string) {
	    // TODO Auto-generated method stub
	    Toast.makeText(getApplicationContext(), p_string, Toast.LENGTH_SHORT).show();
    }
	/* (non-Javadoc)
     * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
     */
    @Override
    public void onCheckedChanged(CompoundButton p_buttonView, boolean p_isChecked) {
	    // TODO Auto-generated method stub
	    if(p_isChecked)
	    {
	    	estadoBoton = true;
	    	bn.setEnabled(estadoBoton);
	    	ch.setText("Boton Activado");
	    }
	    else
	    {
	    	estadoBoton = false;
	    	bn.setEnabled(estadoBoton);
	    	ch.setText("Boton Desactivado");
	    }
    }
    
}