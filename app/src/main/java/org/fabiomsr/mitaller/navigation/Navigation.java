package org.fabiomsr.mitaller.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;

import org.fabiomsr.mitaller.domain.Receipt;
import org.fabiomsr.mitaller.domain.RepairOrder;
import org.fabiomsr.mitaller.module.concept.add.AddReceiptConceptActivity;
import org.fabiomsr.mitaller.module.home.HomeActivity;
import org.fabiomsr.mitaller.module.receipt.add.AddReceiptActivity;
import org.fabiomsr.mitaller.module.receipt.detail.ReceiptDetailActivity;
import org.fabiomsr.mitaller.module.receipt.edit.EditReceiptActivity;
import org.fabiomsr.mitaller.module.repairorder.add.AddRepairOrderActivity;
import org.fabiomsr.mitaller.module.repairorder.detail.RepairOrderDetailActivity;
import org.fabiomsr.mitaller.module.repairorder.edit.EditRepairOrderActivity;

public class Navigation {


  ///
  ///   GO TO ...
  ///

  public static void goToReceiptDetail(Context context, Receipt receipt){
    Intent intent = new Intent(context, ReceiptDetailActivity.class);
    intent.putExtra(ReceiptDetailActivity.PARAM_RECEIPT, receipt);
    context.startActivity(intent);
  }

  public static void goToAddReceipt(Context context){
    goToAddReceipt(context, null);
  }

  public static void goToAddReceipt(Context context, RepairOrder repairOrder){
    Intent intent = new Intent(context, AddReceiptActivity.class);
    intent.putExtra(AddReceiptActivity.PARAM_REPAIR_ORDER, repairOrder);
    context.startActivity(intent);
  }
  public static void goToAddReceiptConcept(Fragment fragment, int requestCode){
    Intent intent = new Intent(fragment.getContext(), AddReceiptConceptActivity.class);
    fragment.startActivityForResult(intent, requestCode);
  }

  public static void goToEditReceipt(Activity activity, int requestCode, Receipt receipt){
    Intent intent = new Intent(activity, EditReceiptActivity.class);
    intent.putExtra(EditReceiptActivity.PARAM_RECEIPT, receipt);
    activity.startActivityForResult(intent, requestCode);
  }

  public static void goToEditRepairOrder(Context context, Fragment fragment, int requestCode, RepairOrder repairOrder){
    Intent intent = new Intent(context, EditRepairOrderActivity.class);
    intent.putExtra(EditRepairOrderActivity.PARAM_REPAIR_ORDER, repairOrder);

    fragment.startActivityForResult(intent, requestCode);
  }

  public static void goToAddRepairOrder(Context context) {
    Intent intent = new Intent(context, AddRepairOrderActivity.class);
    context.startActivity(intent);
  }

  public static void goToRepairOrderDetail(Context context, RepairOrder repairOrder) {
    Intent intent = new Intent(context, RepairOrderDetailActivity.class);
    intent.putExtra(RepairOrderDetailActivity.PARAM_REPAIR_ORDER, repairOrder);
    context.startActivity(intent);
  }

  ///
  ///  UP TO ...
  ///

  public static void upToHome(Activity activity){
    NavUtils.navigateUpTo(activity, new Intent(activity, HomeActivity.class));
  }

  public static void upToReceiptDetail(Activity activity){
    NavUtils.navigateUpTo(activity, new Intent(activity, ReceiptDetailActivity.class));
  }

  public static void upToAddReceipt(Activity activity){
    NavUtils.navigateUpTo(activity, new Intent(activity, AddReceiptActivity.class));
  }


}
