package org.rssms.enums;

/**
 * Created by User on 12.03.2016.
 */
public enum Status {
    OPEN{
        @Override
        public String getUkrainianName(){return "Відкритий";};
    },
    CLOSED{
        @Override
        public String getUkrainianName(){return "Закритий";};
    },
    BANNED{
        @Override
        public String getUkrainianName(){return "Заборонений";};  //Возможно в укр. есть слово лучше
    };

    public abstract String getUkrainianName();
}
