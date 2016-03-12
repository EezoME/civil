package org.rssms.enums;

/**
 * Created by User on 12.03.2016.
 */
public enum Status {
    OPEN{
        @Override
        public String getUkrainianName(){return "³�������";};
    },
    CLOSED{
        @Override
        public String getUkrainianName(){return "��������";};
    },
    BANNED{
        @Override
        public String getUkrainianName(){return "�����������";};  //�������� � ���. ���� ����� �����
    };

    public abstract String getUkrainianName();
}
