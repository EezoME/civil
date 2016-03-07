package enums;

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
    },
    HEALTH_AND_SPORT {
        @Override
        public String getUkrainianName() {
            return "Охорона здоров'я та спорт";
        }
    },
    ENVIRONMENT {
        @Override
        public String getUkrainianName() {
            return "Благоустрій та довкілля";
        }
    },
    ROAD_MAINTANANCE {
        @Override
        public String getUkrainianName() {
            return "Дорожнє господарство";
        }
    },
    CULTURE_AND_EDUCATION {
        @Override
        public String getUkrainianName() {
            return "Культура та освіта";
        }
    },
    MUNICIPAL_INFRASTRUCTURE {
        @Override
        public String getUkrainianName() {
            return "Житлово-комунальна інфраструктура";
        }
    },
    SOCAIL_PROTECTION {
        @Override
        public String getUkrainianName() {
            return "Соціальний захист";
        }
    },
    TRANSPORT {
        @Override
        public String getUkrainianName() {
            return "Транспорт";
        }
    },
    PARKING {
        @Override
        public String getUkrainianName() {
            return "Паркування";
        }
    },
    CITY_PLANNING {
        @Override
        public String getUkrainianName() {
            return "Містобудування";
        }
    },
    SAFS_AND_ELEMENTAL_TRADE {
        @Override
        public String getUkrainianName() {
            return "МАФи та стихійна торгівля";
        }
    },
    ANIMALS {
        @Override
        public String getUkrainianName() {
            return "Тварини";
        }
    },
    OTHER {
        @Override
        public String getUkrainianName() {
            return "Інше";
        }
    }
    ;

    public abstract String getUkrainianName();
}
