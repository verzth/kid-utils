package id.kiosku.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;

import id.kiosku.utils.contracts.DataContact;

/**
 * Created by Dodi on 01/08/2018.
 */

public class ContactDriver {
    private Context context;
    public ContactDriver(Context context){
        this.context = context;
    }
    public static ContactDriver with(Context context){
        return new ContactDriver(context);
    }

    public ArrayList<DataContact> getContacts(){
        ArrayList<DataContact> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,null,null,null,null
        );
        if(cursor!=null){
            while (cursor.moveToNext()){
                DataContact temp = new DataContact();
                temp.ID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                temp.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                //PHONES
                Cursor phonesCursor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",new String[]{temp.ID},null
                );
                if(phonesCursor!=null){
                    HashSet<String> tempPhones = new HashSet<>();
                    while (phonesCursor.moveToNext()){
                        String number = phonesCursor.getString(phonesCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        number=number.replaceAll("[\\s-]","");
                        if(number!=null && !number.isEmpty() && number.length()>10)tempPhones.add(number);
                    }
                    phonesCursor.close();
                    temp.numbers.addAll(tempPhones);
                }

                //EMAILS
                Cursor emailsCursor = context.getContentResolver().query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,null, ContactsContract.CommonDataKinds.Email.CONTACT_ID+"=?",new String[]{temp.ID},null
                );
                if(emailsCursor!=null){
                    HashSet<String> tempEmails = new HashSet<>();
                    while (emailsCursor.moveToNext()){
                        String email = emailsCursor.getString(emailsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                        if(email!=null && !email.isEmpty())tempEmails.add(email);
                    }
                    emailsCursor.close();
                    temp.emails.addAll(tempEmails);
                }
                if(temp.numbers.size()>0 || temp.emails.size()>0)list.add(temp);
            }
            cursor.close();
        }
        return list;
    }
    public String getContactsJson(){
        return new Gson().toJson(getContacts());
    }
}
