package vn.com.hkt.pilot.ui.setup;
/*************************************************************************
 * 																		 *
 *  Cach dung lop AutoCompleteCombox                                     *
 * 																		 *
 *  Buoc 1: Tao 1 DefaultComboBoxModel									 *
 *  																	 *
 *  Buoc 2: Tao 1 AutoCompleteCombox la 1 JComboBox						 *
 *  																	 *
 *  Set Model cho AutoCompleteCombox 									 *
 *  																	 *
 *  VD: AutoCompleteCombox cboBox = new AutoCompleteCombox(model)		 *
 *  																	 *
 *  Nhu vay co the su dung ComboBox binh thuong roi.					 *
 * 																		 *
 * 																		 *
 *************************************************************************/


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.Timer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * 
 * @author HKT01
 *
 */

public class AutoCompleteComboAPI extends JComboBox{

    private Model model;
    private final JTextComponent textComponent = (JTextComponent) getEditor().getEditorComponent();
    private boolean modelFilling = false;

    private boolean updatePopup;

    public AutoCompleteComboAPI(DefaultComboBoxModel comboBoxModel) {

    	model = new Model(comboBoxModel);
        setEditable(true);

        setPattern(null);
        updatePopup = false;
        
        textComponent.setDocument(new AutoCompleteDocument());
        setModel(model);
        setSelectedItem(null);

        new Timer(20, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (updatePopup && isDisplayable()) {
                    setPopupVisible(false);
                    if (model.getSize() > 0) {
                        setPopupVisible(true);
                    }
                    updatePopup = false;
                }
            }
        }).start();
    }

      private class AutoCompleteDocument extends PlainDocument {

        boolean arrowKeyPressed = false;

        public AutoCompleteDocument() {
            textComponent.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        String text = textComponent.getText();
                        if (!model.data.contains(text)) {
                            addToTop(text);
                        }
                    } else if (key == KeyEvent.VK_UP ||
                               key == KeyEvent.VK_DOWN) {
                        arrowKeyPressed = true;
                    }
                }
            });
        }

        void updateModel() throws BadLocationException {
            String textToMatch = getText(0, getLength());
            setPattern(textToMatch);
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {

            if (modelFilling) {
                return;
            }

            super.remove(offs, len);
            if (arrowKeyPressed) {
                arrowKeyPressed = false;
            } else {
                updateModel();
            }
            clearSelection();
        }
        
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

            if (modelFilling) {
                return;
            }

            super.insertString(offs, str, a);
            String text = getText(0, getLength());
            if (arrowKeyPressed) {
                model.setSelectedItem(text);
                arrowKeyPressed = false;
            } else if(!text.equals(getSelectedItem())){
                updateModel();
            }

            clearSelection();
        }

    }
    public void setText(String text) {
        if (model.data.contains(text)) {
            setSelectedItem(text);
        } else {
            addToTop(text);
            setSelectedIndex(0);
        }
    }

    public String getText() {
        return getEditor().getItem().toString();
    }

    private String previousPattern = null;

    private void setPattern(String pattern) {

        if(pattern!=null && pattern.trim().isEmpty())
            pattern = null;

        if(previousPattern==null && pattern ==null ||
           pattern!=null && pattern.equals(previousPattern)) {
            return;
        }
        previousPattern = pattern;
        modelFilling = true;
        model.setPattern(pattern);      
            StringBuilder b = new StringBuilder(100);
            b.append("pattern filter '").append(pattern==null ? "null" : pattern).append("' set:\n");
            for(int i=0; i<model.getSize(); i++) {
                b.append(", ").append('[').append(model.getElementAt(i)).append(']');
            }
            int ind = b.indexOf(", ");
            if(ind != -1) {
                b.delete(ind, ind+2);
            }
        modelFilling = false;
        if(pattern != null)
            updatePopup = true;
    }


    private void clearSelection() {
        int i = getText().length();
        textComponent.setSelectionStart(i);
        textComponent.setSelectionEnd(i);
    }


    public synchronized void addToTop(String aString) {
        model.addToTop(aString);
    }

      private class Model extends AbstractListModel implements ComboBoxModel {

        String selected;
        final String delimiter = ";;;";
        final int limit = 20;

        class Data {

            private List<String> list = new ArrayList<String>(limit);
            private List<String> lowercase = new ArrayList<String>(limit);
            private List<String> filtered;

            void add(String s) {
                list.add(s);
                lowercase.add(s.toLowerCase());
            }

            void addToTop(String s) {
                list.add(0, s);
                lowercase.add(0, s.toLowerCase());
            }

            void remove(int index) {
                list.remove(index);
                lowercase.remove(index);
            }

            List<String> getList() {
                return list;
            }

            List<String> getFiltered() {
                if(filtered==null)
                    filtered = list;
                return filtered;
            }

            int size() {
                return list.size();
            }

            void setPattern(String pattern) {
                if (pattern == null || pattern.isEmpty()) {
                    filtered = list;
                    AutoCompleteComboAPI.this.setSelectedItem(model.getElementAt(0));
                } else {
                    filtered = new ArrayList<String>(limit);
                    pattern = pattern.toLowerCase();
                    for(int i=0; i<lowercase.size(); i++) {
                        //case insensitive search
                        if (lowercase.get(i).contains(pattern)) {
                            filtered.add( list.get(i) );
                        }
                    }
                    AutoCompleteComboAPI.this.setSelectedItem(pattern);
                }
            }

            boolean contains(String s) {
                if(s==null || s.trim().isEmpty())
                    return true;
                s = s.toLowerCase();
                for (String item : lowercase) {
                    if (item.equals(s)) {
                        return true;
                    }
                }
                return false;
            }
        }

        Data data = new Data();

          void readData(DefaultComboBoxModel comboBoxModel) {
        	  
        	  List<String> list = new ArrayList<String>();
        	  int i;
        	  for(i=0;i<comboBoxModel.getSize();i++){
        		  String row = comboBoxModel.getElementAt(i).toString();
        		  list.add(row);
        	  }
        	  
        	  if(!list.isEmpty()){
        		  for (String rows : list) {
                      data.add(rows);
                  } 
        	  }
              
          }

          boolean isThreadStarted = false;

          void writeData() {
              StringBuilder b = new StringBuilder(limit * 60);

              for (String url : data.getList()) {
                  b.append(delimiter).append(url);
              }
              b.delete(0, delimiter.length());

              //waiting thread is already being run
              if (isThreadStarted) {
                  return;
              }

              //we do saving in different thread
              //for optimization reasons (saving may take much time)
              new Thread(new Runnable() {

                  @Override
                  public void run() {
                      //we do sleep because saving operation
                      //may occur more than one per waiting period
                      try {
                          Thread.sleep(2000);
                      } catch (InterruptedException ex) {
                      }
                      //we need this synchronization to
                      //synchronize with AutoCompleteCombo.addElement method
                      //(race condition may occur)
                      synchronized (AutoCompleteComboAPI.this) {

                          //HERE MUST BE SAVING OPERATION
                          //(SAVING INTO FILE OR SOMETHING)
                          //don't forget replace readData() method
                          //to read saved data when creating bean

                          isThreadStarted = false;
                      }
                  }
              }).start();
              isThreadStarted = true;
          }

        public Model(DefaultComboBoxModel model) {
            readData(model);
        }

        public void setPattern(String pattern) {

            int size1 = getSize();

            data.setPattern(pattern);

            int size2 = getSize();

            if(size1<size2) {
                fireIntervalAdded(this, size1, size2-1);
                fireContentsChanged(this, 0, size1-1);
            } else if(size1>size2) {
                fireIntervalRemoved(this, size2, size1-1);
                fireContentsChanged(this, 0, size2-1);
            }
        }

        public void addToTop(String aString) {
            if(aString==null || data.contains(aString))
                return;
            if(data.size()==0)
                data.add(aString);
            else
                data.addToTop(aString);

            while(data.size()>limit) {
                int index = data.size()-1;
                data.remove(index);
            }

            setPattern(null);
            model.setSelectedItem(aString);

            //saving into options
            if (data.size() > 0) {
                writeData();
            }
        }

        @Override
        public Object getSelectedItem() {
            return selected;
        }

          @Override
          public void setSelectedItem(Object anObject) {
              if ((selected != null && !selected.equals(anObject)) ||
                      selected == null && anObject != null) {
                  selected = (String) anObject;
                  fireContentsChanged(this, -1, -1);
              }
          }

        @Override
        public int getSize() {
            return data.getFiltered().size();
        }

        @Override
        public Object getElementAt(int index) {
            return data.getFiltered().get(index);
        }

    }

}