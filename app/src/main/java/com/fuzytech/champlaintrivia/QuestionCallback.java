package com.fuzytech.champlaintrivia;

import java.io.Serializable;

public interface QuestionCallback extends Serializable {

    public void invoke(boolean correct);

}
