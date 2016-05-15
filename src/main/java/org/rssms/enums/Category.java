package org.rssms.enums;

/**
 *
 * Created by Eezo on 07.03.2016.
 */
public enum Category {
    RUBBISH {
        @Override
        public String getUkrainianName() {
            return "Сміття";
        }

        @Override
        public String getTagColor() {
            return "darkred";
        }
    },
    HEALTH_AND_SPORT {
        @Override
        public String getUkrainianName() {
            return "Охорона здоров'я та спорт";
        }

        @Override
        public String getTagColor() {
            return "darkkhaki";
        }
    },
    ENVIRONMENT {
        @Override
        public String getUkrainianName() {
            return "Благоустрій та довкілля";
        }

        @Override
        public String getTagColor() {
            return "darkolivegreen";
        }
    },
    ROAD_MAINTANANCE {
        @Override
        public String getUkrainianName() {
            return "Дорожнє господарство";
        }

        @Override
        public String getTagColor() {
            return "dimgray";
        }
    },
    CULTURE_AND_EDUCATION {
        @Override
        public String getUkrainianName() {
            return "Культура та освіта";
        }

        @Override
        public String getTagColor() {
            return "darkblue";
        }
    },
    MUNICIPAL_INFRASTRUCTURE {
        @Override
        public String getUkrainianName() {
            return "Житлово-комунальна інфраструктура";
        }

        @Override
        public String getTagColor() {
            return "darkmagenta";
        }
    },
    SOCAIL_PROTECTION {
        @Override
        public String getUkrainianName() {
            return "Соціальний захист";
        }

        @Override
        public String getTagColor() {
            return "darkcyan";
        }
    },
    TRANSPORT {
        @Override
        public String getUkrainianName() {
            return "Транспорт";
        }

        @Override
        public String getTagColor() {
            return "mediumslateblue";
        }
    },
    PARKING {
        @Override
        public String getUkrainianName() {
            return "Паркування";
        }

        @Override
        public String getTagColor() {
            return "peru";
        }
    },
    CITY_PLANNING {
        @Override
        public String getUkrainianName() {
            return "Містобудування";
        }

        @Override
        public String getTagColor() {
            return "olive";
        }
    },
    SAFS_AND_ELEMENTAL_TRADE {
        @Override
        public String getUkrainianName() {
            return "МАФи та стихійна торгівля";
        }

        @Override
        public String getTagColor() {
            return "firebrick";
        }
    },
    ANIMALS {
        @Override
        public String getUkrainianName() {
            return "Тварини";
        }

        @Override
        public String getTagColor() {
            return "forestgreen";
        }
    },
    OTHER {
        @Override
        public String getUkrainianName() {
            return "Інше";
        }

        @Override
        public String getTagColor() {
            return "gray";
        }
    }
    ;

    public abstract String getUkrainianName();
    public abstract String getTagColor();
}
