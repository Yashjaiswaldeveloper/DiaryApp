package com.eventstore.bookdatabase.diaryapp;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;
import java.util.Random;

public class SettingsActivity extends AppCompatActivity {
	
	public final int REQ_CD_PICK = 101;
	
	private double filess = 0;
	private double img = 0;
	private double tdf = 0;
	private String pathpick = "";
	
	private ArrayList<String> files = new ArrayList<>();
	private ArrayList<String> images = new ArrayList<>();
	private ArrayList<String> todofol = new ArrayList<>();
	
	private LinearLayout linear1;
	private ProgressBar progressbar1;
	private ScrollView vscroll1;
	private ImageView back;
	private TextView textview1;
	private LinearLayout linear2;
	private TextView textview4;
	private LinearLayout backup;
	private LinearLayout restore;
	private LinearLayout linear3;
	private ImageView imageview1;
	private TextView textview2;
	private ImageView imageview2;
	private TextView textview3;
	private ImageView imageview3;

	private ImageView imageview4;
	private TextView textview6;
	private ImageView imageview5;
	private TextView textview7;
	
	private Intent intent = new Intent();
	private AlertDialog.Builder dbl;
	private AlertDialog.Builder sgl;
	private Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		AppManage.getInstance(SettingsActivity.this).loadInterstitialAd(this);
		AppManage.getInstance(SettingsActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		back = (ImageView) findViewById(R.id.back);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview4 = (TextView) findViewById(R.id.textview4);
		backup = (LinearLayout) findViewById(R.id.backup);
		restore = (LinearLayout) findViewById(R.id.restore);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		dbl = new AlertDialog.Builder(this);
		sgl = new AlertDialog.Builder(this);
		pick.setType("*/*");
		pick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				AppManage.getInstance(SettingsActivity.this).showInterstitialAd(SettingsActivity.this, new AppManage.MyCallback() {
					public void callbackCall() {
						intent.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(intent);
						finish();
					}
				}, "", AppManage.app_mainClickCntSwAd);

			}
		});
		
		backup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				progressbar1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.INVISIBLE);
				FileUtil.listDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database"), files);
				FileUtil.listDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/images"), images);
				FileUtil.listDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/todofolder"), todofol);
				filess = 0;
				for(int _repeat28 = 0; _repeat28 < (int)(files.size()); _repeat28++) {
					FileUtil.copyFile(files.get((int)(filess)), FileUtil.getExternalStorageDir().concat("/aio/myday/backup/db/".concat(Uri.parse(files.get((int)(filess))).getLastPathSegment())));
					filess++;
				}
				FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/db/".concat("images")));
				FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/db/".concat("todofolder")));
				img = 0;
				for(int _repeat29 = 0; _repeat29 < (int)(images.size()); _repeat29++) {
					FileUtil.copyFile(images.get((int)(img)), FileUtil.getExternalStorageDir().concat("/aio/myday/backup/img/".concat(Uri.parse(images.get((int)(img))).getLastPathSegment())));
					img++;
				}
				tdf = 0;
				for(int _repeat30 = 0; _repeat30 < (int)(todofol.size()); _repeat30++) {
					FileUtil.copyFile(todofol.get((int)(tdf)), FileUtil.getExternalStorageDir().concat("/aio/myday/backup/tdf/".concat(Uri.parse(todofol.get((int)(tdf))).getLastPathSegment())));
					tdf++;
				}
				FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/restore.aio"), "Use this file to find restore file path if u use different location for backup.\ndefault restore will auto find file at backup. if path is none it will ask u to pick a file.. this file u must pick");
				sgl.setTitle("Success");
				sgl.setMessage("Backup at : ".concat(FileUtil.getExternalStorageDir().concat("/aio/myday/backup")));
				sgl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				sgl.create().show();
				progressbar1.setVisibility(View.GONE);
				linear2.setVisibility(View.VISIBLE);
			}
		});
		
		restore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View _view) {
				AppManage.getInstance(SettingsActivity.this).showInterstitialAd(SettingsActivity.this, new AppManage.MyCallback() {
					public void callbackCall() {
						linear2.setVisibility(View.INVISIBLE);
						progressbar1.setVisibility(View.VISIBLE);
						_restoree();
					}
				}, "", AppManage.app_mainClickCntSwAd);

			}
		});

	}
	private void initializeLogic() {
		progressbar1.setVisibility(View.GONE);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PICK:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				linear2.setVisibility(View.INVISIBLE);
				progressbar1.setVisibility(View.VISIBLE);
				_restoreFromPick(_filePath.get((int)(0)));
				sgl.setTitle("Success");
				sgl.setMessage("Restore Success");
				sgl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				sgl.create().show();
				progressbar1.setVisibility(View.GONE);
				linear2.setVisibility(View.VISIBLE);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		intent.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(intent);
		finish();
	}
	private void _restoree () {
		files.clear();
		images.clear();
		todofol.clear();
		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/restore.aio"))) {
			FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/db"), files);
			FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/img"), images);
			FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/aio/myday/backup/tdf"), todofol);
			filess = 0;
			for(int _repeat31 = 0; _repeat31 < (int)(files.size()); _repeat31++) {
				FileUtil.copyFile(files.get((int)(filess)), FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/".concat(Uri.parse(files.get((int)(filess))).getLastPathSegment())));
				filess++;
			}
			filess = 0;
			for(int _repeat46 = 0; _repeat46 < (int)(images.size()); _repeat46++) {
				FileUtil.copyFile(images.get((int)(filess)), FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/images/".concat(Uri.parse(images.get((int)(filess))).getLastPathSegment())));
				filess++;
			}
			filess = 0;
			for(int _repeat59 = 0; _repeat59 < (int)(todofol.size()); _repeat59++) {
				FileUtil.copyFile(todofol.get((int)(filess)), FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/todofolder/".concat(Uri.parse(todofol.get((int)(filess))).getLastPathSegment())));
				filess++;
			}
			sgl.setTitle("Success");
			sgl.setMessage("Restore Success");
			sgl.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			sgl.create().show();
		}
		else {
			dbl.setTitle(":(");
			dbl.setMessage("Backup data not found. please pick file \"restore.aio\" for auto restore.");
			dbl.setPositiveButton("Pick File", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					startActivityForResult(pick, REQ_CD_PICK);
				}
			});
			dbl.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					
				}
			});
			dbl.create().show();
		}
		linear2.setVisibility(View.VISIBLE);
		progressbar1.setVisibility(View.GONE);
	}
	
	
	private void _restoreFromPick (final String _path) {
		if (_path.contains("restore.aio")) {
			pathpick = _path.replace("/restore.aio", "");
			files.clear();
			images.clear();
			todofol.clear();
			FileUtil.listDir(pathpick.concat("/db"), files);
			FileUtil.listDir(pathpick.concat("/img"), images);
			FileUtil.listDir(pathpick.concat("/tdf"), todofol);
			filess = 0;
			for(int _repeat19 = 0; _repeat19 < (int)(files.size()); _repeat19++) {
				FileUtil.copyFile(files.get((int)(filess)), FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/".concat(Uri.parse(files.get((int)(filess))).getLastPathSegment())));
				filess++;
			}
			filess = 0;
			for(int _repeat44 = 0; _repeat44 < (int)(images.size()); _repeat44++) {
				FileUtil.copyFile(images.get((int)(filess)), FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/images/".concat(Uri.parse(images.get((int)(filess))).getLastPathSegment())));
				filess++;
			}
			filess = 0;
			for(int _repeat57 = 0; _repeat57 < (int)(todofol.size()); _repeat57++) {
				FileUtil.copyFile(todofol.get((int)(filess)), FileUtil.getPackageDataDir(getApplicationContext()).concat("/.database/todofolder/".concat(Uri.parse(todofol.get((int)(filess))).getLastPathSegment())));
				filess++;
			}
		}
		else {
			arsybaiUtil.showMessage(getApplicationContext(), "Please pick \"restore.aio\" file");
		}
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
