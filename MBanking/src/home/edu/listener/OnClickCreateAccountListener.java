package home.edu.listener;

import com.hqsolution.hqserver.app.dao.AccountLogin;

import home.edu.CreateAccountActivity;
import home.edu.common.FinancialRequest;
import home.edu.common.FinancialRequestFacade;
import android.accounts.Account;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class OnClickCreateAccountListener implements View.OnClickListener,
		Runnable {

	private CreateAccountActivity activity;

	public OnClickCreateAccountListener(CreateAccountActivity activity) {
		super();
		this.activity = activity;
	}

	public void run() {
		FinancialRequest facade = FinancialRequestFacade.getInstance();
		AccountLogin login = new AccountLogin();
		login.setFullname(activity.getName().getText().toString());
		login.setEmail(activity.getEmail().getText().toString());
		login.setPassword(activity.getPassword().getText().toString());
		facade.saveUserInfo(login);
		activity.getProgressDialog().dismiss();
	}

	public void onClick(View v) {
		ProgressDialog pd = activity.showProgressDialog();
		ProgressBar bar = new ProgressBar(activity);
		pd.addContentView(bar, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));
		Thread thread = new Thread(this);
		thread.start();
	}
}
