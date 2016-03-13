package org.rssms.enums;

/**
 *
 * Created by Eezo on 07.03.2016.
 */
public enum Role {
    UNCONFIRMED {
        @Override
        public  String getUkrainianName() {
            return "Не підтверджений";
        }
    },
    SIMPLE {
        @Override
        public String getUkrainianName() {
            return "Простий";
        }
    },
    PRIVILEGED {
        @Override
        public String getUkrainianName() {
            return "Привілегійований";
        }
    },
    MODERATOR {
        @Override
        public String getUkrainianName() {
            return "Модератор";
        }
    },
    ADMINISTRATOR {
        @Override
        public String getUkrainianName() {
            return "Адміністратор";
        }
    }
    ;
    public abstract String getUkrainianName();
}
