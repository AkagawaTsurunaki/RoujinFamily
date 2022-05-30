package com.github.akagawatsurunaki.roujinfamily.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.User;
import com.github.akagawatsurunaki.roujinfamily.view.Frame;
import com.github.akagawatsurunaki.roujinfamily.view.LoginFrame;

public class Controller {
	// Show an error message box on current window with error message and title. 
	// It will disable substratum windows. 
	
	//protected Frame mainFrame;
	protected DateTimeFormatter glbDateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");

	// #region Show Error Message Box Methods
	
	protected void showErrorMessageBox(RouJinFamilyException e, Frame frame) {
		String msg = "错误信息：" + e.getErrorMessage() + "\n发起者：" + e.getPositionInfo();
		String title = e.getTitle();
		JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.OK_OPTION);
	}
	
	protected void showErrorMessageBox(String msg, String title, String pos, Frame frame) {
		RouJinFamilyException e = new RouJinFamilyException(msg, title, pos);
		showErrorMessageBox(e, frame);
	}
	
	// #endregion
	
	protected void setGenderRadio(JRadioButton maleBtn, Member member) {
		if (member.getGender() == Gender.MALE) {
			maleBtn.setSelected(true);
		}
		else {
			maleBtn.setSelected(false);
		}
	}
	
	protected void setGenderRadio(JRadioButton maleBtn, User user) {
		if (user.getGender() == Gender.MALE) {
			maleBtn.setSelected(true);
		}
		else {
			maleBtn.setSelected(false);
		}
	}
	
	protected Gender getGenderFromRdBtn(JRadioButton maleBtn) {
		
		if (maleBtn.isSelected()) {
			return Gender.MALE;
		}
		else {
			return Gender.FEMALE;
		}
	}
	
	protected <T extends Member> void  updateComboBoxWithRealName(List<T> list, JComboBox<String> cbBox) { 
		cbBox.removeAllItems();
		for(T t : list) {
			cbBox.addItem(t.getRealName());
		}
	}
	

	
}
