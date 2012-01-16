package com.github.cruscotto;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TopicContainer extends Composite {

	private Composite mContentArea;
	private Label mHeader;
	private Color mHeaderBgColor;
	private Color mHeaderFgColor;
	private boolean collapsed = false;
	private boolean focused = false;

	public TopicContainer(final Cruscotto cruscotto, String title) {
		super(cruscotto.getTopContainer(), SWT.NONE);
		mHeaderBgColor = new Color(getDisplay(), 100, 100, 255);
		mHeaderFgColor = new Color(getDisplay(), 255, 255, 255);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		GridLayout layout = new GridLayout(3, false);
//		setBackground(mHeaderFgColor);
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.horizontalSpacing = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		// create header
		Button expand = new Button(this, SWT.TOGGLE | SWT.FLAT);
		expand.setText("X");
		expand.setToolTipText("Collapse/Expand topic");
		expand.setLayoutData(new GridData(SWT.LEAD, SWT.BEGINNING, false, false));

		Button focus = new Button(this, SWT.TOGGLE | SWT.FLAT);
		focus.setText("@");
		focus.setToolTipText("Extend/Restore topic");
		focus.setLayoutData(new GridData(SWT.LEAD, SWT.BEGINNING, false, false));

		mHeader = new Label(this, SWT.NONE);
		mHeader.setText(title);
		mHeader.setBackground(mHeaderBgColor);
		mHeader.setForeground(mHeaderFgColor);
		mHeader.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		// create a container for the content
		mContentArea = new Composite(this, SWT.NULL);
		mContentArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
//		mContentArea.setBackground(mHeaderFgColor);
		createContent(mContentArea);

		expand.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// collapse TopicContainer (subclass of composite)
				// by setting its height to zero;
				collapsed = !collapsed;
				reLayout(cruscotto);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		focus.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// collapse TopicContainer (subclass of composite)
				// by setting its height to zero;
				focused = !focused;
				reLayout(cruscotto);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	
	private void reLayout(final Cruscotto cruscotto) {
		GridData contentGD = (GridData) mContentArea.getLayoutData();
		contentGD.exclude = collapsed;
		contentGD.grabExcessHorizontalSpace = focused;
		GridData topicGD = (GridData) getLayoutData();
		topicGD.grabExcessVerticalSpace = collapsed ? false : focused;
		cruscotto.getTopContainer().layout(true, true);
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
