package vn.trungnq.testapp;

import android.app.Service;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import vn.trungnq.testapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnFocusChangeListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /*binding.pincode1.requestFocus();

        binding.pincode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.pincode1.getText().toString().length() != 0) {
                    binding.pincode1.clearFocus();
                    binding.pincode2.requestFocus();
                    //binding.pincode1.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                } else {
                    binding.pincode1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.pincode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.pincode2.getText().toString().length() != 0) {
                    binding.pincode2.clearFocus();
                    binding.pincode3.requestFocus();
                    //binding.pincode2.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                } else {
                    binding.pincode2.clearFocus();
                    binding.pincode1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.pincode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.pincode3.getText().toString().length() != 0) {
                    binding.pincode3.clearFocus();
                    binding.pincode4.requestFocus();
                    //binding.pincode3.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                } else {
                    binding.pincode3.clearFocus();
                    binding.pincode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.pincode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.pincode4.getText().toString().length() != 0) {
                    binding.pincode4.requestFocus();
                    //binding.pincode4.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                } else {
                    binding.pincode4.clearFocus();
                    binding.pincode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        setEditTextListener();

        binding.pincode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("[trungnq]", "pincode1");
                setFocus(binding.hiddenInput);
                showSoftKeyboard(binding.hiddenInput);
            }
        });

        binding.pincode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("[trungnq]", "pincode2");
            }
        });

        binding.pincode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("[trungnq]", "pincode3");
            }
        });

        binding.pincode4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("[trungnq]", "pincode4");
            }
        });

    }

    private void setEditTextListener() {
        binding.hiddenInput.addTextChangedListener(textWatcher);

        binding.pincode1.setOnFocusChangeListener(this);
        binding.pincode2.setOnFocusChangeListener(this);
        binding.pincode3.setOnFocusChangeListener(this);
        binding.pincode4.setOnFocusChangeListener(this);

        binding.pincode1.setOnKeyListener(this);
        binding.pincode2.setOnKeyListener(this);
        binding.pincode3.setOnKeyListener(this);
        binding.pincode4.setOnKeyListener(this);
        binding.hiddenInput.setOnKeyListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.pincode_1:
            case R.id.pincode_2:
            case R.id.pincode_3:
            case R.id.pincode_4:
                if (hasFocus) {
                    setFocus(binding.hiddenInput);
                    showSoftKeyboard(binding.hiddenInput);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            switch (view.getId()) {
                case R.id.hidden_input:
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (binding.hiddenInput.getText().length() == 4) {
                            binding.pincode4.setText("");
                        } else if (binding.hiddenInput.getText().length() == 3) {
                            binding.pincode3.setText("");
                        } else if (binding.hiddenInput.getText().length() == 2) {
                            binding.pincode2.setText("");
                        } else if (binding.hiddenInput.getText().length() == 1) {
                            binding.pincode1.setText("");
                        }

                        if (binding.hiddenInput.length() > 0) {
                            binding.hiddenInput
                                    .setText(binding.hiddenInput.getText()
                                            .subSequence(0, binding
                                                    .hiddenInput.length() - 1));
                            binding.hiddenInput.setSelection(binding.hiddenInput.getText().length());
                        }

                        return true;
                    }
                    break;
                default:
                    return false;
            }
        }

        return false;
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                binding.pincode1.setText("");
                binding.pincode2.setText("");
                binding.pincode3.setText("");
                binding.pincode4.setText("");
            } else if (s.length() == 1) {
                binding.pincode1.setText(s.charAt(0) + "");
                binding.pincode2.setText("");
                binding.pincode3.setText("");
                binding.pincode4.setText("");
            } else if (s.length() == 2) {
                binding.pincode2.setText(s.charAt(1) + "");
                binding.pincode3.setText("");
                binding.pincode4.setText("");
            } else if (s.length() == 3) {
                binding.pincode3.setText(s.charAt(2) + "");
                binding.pincode4.setText("");
            } else if (s.length() == 4) {
                binding.pincode4.setText(s.charAt(3) + "");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void setFocus(EditText editText) {
        if (editText == null) return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    private void showSoftKeyboard(EditText editText) {
        if (editText == null) return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    private void hideSoftKeyboard(EditText editText) {
        if (editText == null) return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
