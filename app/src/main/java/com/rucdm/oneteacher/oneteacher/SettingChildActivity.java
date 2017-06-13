package com.rucdm.oneteacher.oneteacher;

import java.io.File;
import java.lang.reflect.Method;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;

import com.rucdm.oneteacher.oneteacher.settingchild.SetAboutUsFragment;
import com.rucdm.oneteacher.oneteacher.settingchild.SetQuestionsFragment;
import com.rucdm.oneteacher.oneteacher.settingchild.SetUseHelpFragment;

public class SettingChildActivity extends FragmentActivity {

	private static final String SETTINGCHILDPOSITION = "SETTINGCHILDPOSITION";
	private static final int CHANGEPWD = 60;
	private static final int CLEARCACHE = 61;
	private static final int USEHELP = 62;
	private static final int QUESTIONS = 63;
	private static final int ABOUTUS = 64;
	private SetUseHelpFragment sUseHelpFragment = null;
	private SetQuestionsFragment sQuestionsFragment = null;
	private SetAboutUsFragment sAboutUsFragment = null;
	private FrameLayout fl_setting_child_act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_child);
		onNewIntent(getIntent());
		initLayout();
		initData();
	}

	private void initData() {
	}

	private void initLayout() {
		fl_setting_child_act = (FrameLayout) findViewById(R.id.fl_setting_child_act);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		FragmentTransaction beginTransaction = getSupportFragmentManager()
				.beginTransaction();
		int position = intent.getIntExtra(SETTINGCHILDPOSITION, -1);
		switch (position) {


		case USEHELP:
			if (sUseHelpFragment == null) {
				sUseHelpFragment = new SetUseHelpFragment();
				beginTransaction.add(R.id.fl_setting_child_act,
						sUseHelpFragment);
			} else {
				beginTransaction.remove(sUseHelpFragment);
				sUseHelpFragment = new SetUseHelpFragment();
				beginTransaction.add(R.id.fl_setting_child_act,
						sUseHelpFragment);
			}
			break;

		case QUESTIONS:
			if (sQuestionsFragment == null) {
				sQuestionsFragment = new SetQuestionsFragment();
				beginTransaction.add(R.id.fl_setting_child_act,
						sQuestionsFragment);
			} else {
				beginTransaction.remove(sQuestionsFragment);
				sQuestionsFragment = new SetQuestionsFragment();
				beginTransaction.add(R.id.fl_setting_child_act,
						sQuestionsFragment);
			}
			break;

		case ABOUTUS:
			if (sAboutUsFragment == null) {
				sAboutUsFragment = new SetAboutUsFragment();
				beginTransaction.add(R.id.fl_setting_child_act,
						sAboutUsFragment);
			} else {
				beginTransaction.remove(sAboutUsFragment);
				sAboutUsFragment = new SetAboutUsFragment();
				beginTransaction.add(R.id.fl_setting_child_act,
						sAboutUsFragment);
			}
			break;

		default:
			break;
		}
		beginTransaction.commit();
	}

}
