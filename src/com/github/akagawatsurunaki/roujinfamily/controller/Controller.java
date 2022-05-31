package com.github.akagawatsurunaki.roujinfamily.controller;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import com.github.akagawatsurunaki.roujinfamily.exception.RouJinFamilyException;
import com.github.akagawatsurunaki.roujinfamily.model.Gender;
import com.github.akagawatsurunaki.roujinfamily.model.Member;
import com.github.akagawatsurunaki.roujinfamily.model.RegularBus;
import com.github.akagawatsurunaki.roujinfamily.model.User;
public abstract class Controller {
	
	// #region Abstract Methods
	
	public abstract void showMainFrame();
	
	// #endregion
	
	// #region Show Error Message Box Methods
	
	protected void showErrorMessageBox(RouJinFamilyException e, JFrame frame) {
		String msg = "错误信息：" + e.getErrorMessage() + "\n发起者：" + e.getPositionInfo();
		String title = e.getTitle();
		JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.OK_OPTION);
	}
	
	protected void showErrorMessageBox(String msg, String title, String pos, JFrame frame) {
		RouJinFamilyException e = new RouJinFamilyException(msg, title, pos);
		showErrorMessageBox(e, frame);
	}
	
	// #endregion
	
	// #region Swing Component Update Methods
	
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
	
	protected <T extends Member> void  updateComboBoxWithRealName(List<T> list, JComboBox<String> cmbBox) { 
		cmbBox.removeAllItems();
		for(T t : list) {
			cmbBox.addItem(t.getRealName());
		}
	}
	
	protected void updateComboBoxWithId(List<RegularBus> list, JComboBox<String> cmbBox) {
		cmbBox.removeAllItems();
		for(RegularBus bus : list) {
			cmbBox.addItem(String.valueOf(bus.getId()));
		}
	}
	
	// #endregion
	
}
