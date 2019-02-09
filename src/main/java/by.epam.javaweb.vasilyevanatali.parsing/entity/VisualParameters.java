package by.epam.javaweb.vasilyevanatali.parsing.entity;

public class VisualParameters {
    private String stemColor;
    private String leafColor;
    private int averageSize;

    public VisualParameters() {
    }

    public VisualParameters(String stemColor, String leafColor, int averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public String getStemColor() {
        return stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        VisualParameters visualParameters = (VisualParameters) o;
        if (averageSize != visualParameters.averageSize) {
            return false;
        }
        if (stemColor == null) {
            if (visualParameters.stemColor != null) {
                return false;
            }
        } else if (!stemColor.equals(visualParameters.stemColor)) {
            return false;
        }
        if (leafColor == null) {
            return visualParameters.leafColor == null;
        } else return leafColor.equals(visualParameters.leafColor);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (stemColor != null ? stemColor.hashCode() : 0);
        result = prime * result + (leafColor != null ? leafColor.hashCode() : 0);
        result = prime * result + averageSize;
        return result;
    }
}
