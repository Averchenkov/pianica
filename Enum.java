package Pianica;

public class Enum {
    enum Mast{
        CHERVI {
            public String toString() {
                return "CHERVI";
            }
        },
        BUBI {
            public String toString() {
                return "BUBI";
            }
        },
        KRESTI {
            public String toString() {
                return "KRESTI";
            }
        },
        PIKI {
            public String toString() {
                return "PIKI";
            }
        }
    }

    enum Rank{
        SIX {
            public String toString() {
                return "SIX";
            }
        },
        SEVEN {
            public String toString() {
                return "SEVEN";
            }
        },
        EIGHT {
            public String toString() {
                return "EIGHT";
            }
        },
        NINE {
            public String toString() {
                return "NINE";
            }
        },
        TEN {
            public String toString() {
                return "TEN";
            }
        },
        VALET {
            public String toString() {
                return "VALET";
            }
        },
        DAMA {
            public String toString() {
                return "DAMA";
            }
        },
        KAROL {
            public String toString() {
                return "KAROL";
            }
        },
        TUZ {
            public String toString() {
                return "TUZ";
            }
        }
    }
}
