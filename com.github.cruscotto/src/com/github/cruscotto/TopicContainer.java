package com.github.cruscotto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TopicContainer extends Composite {

	private Composite mContentArea;
	private Label mHeader;
	private Color mHeaderBgColor;
	private Color mHeaderFgColor;

	public TopicContainer(Cruscotto cruscotto, String title) {
		super(cruscotto.getTopContainer(), SWT.NONE);
		mHeaderBgColor = new Color(getDisplay(), 100, 100, 255);
		mHeaderFgColor = new Color(getDisplay(), 255, 255, 255);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		GridLayout layout = new GridLayout();
		setBackground(mHeaderFgColor);
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.horizontalSpacing = 0;
		layout.marginWidth = 0;
		setLayout(layout);

		// create header
		mHeader = new Label(this, SWT.NONE);
		mHeader.setText(title);
		mHeader.setBackground(mHeaderBgColor);
		mHeader.setForeground(mHeaderFgColor);
		mHeader.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		
		// create a container for the content
		mContentArea = new Composite(this, SWT.NULL);
		mContentArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mContentArea.setBackground(mHeaderFgColor);
		createContent(mContentArea);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (mHeaderBgColor != null)
			mHeaderBgColor.dispose();
		if (mHeaderFgColor != null)
			mHeaderFgColor.dispose();
	}
	public void createContent(Composite contentArea) {
		
	}
}
