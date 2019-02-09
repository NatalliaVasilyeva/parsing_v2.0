package by.epam.javaweb.vasilyevanatali.parsing.entity;

import java.time.LocalDate;


public class Flower {

    private String id;
    private String name;
    private LocalDate plantingDate;
    private String origin;
    private Soil soilType;
    private Multiplying multiplying;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;

    public Flower() {
        visualParameters = new VisualParameters();
        growingTips = new GrowingTips();
    }

    public Flower(String id, String name, LocalDate plantingDate, String origin, Soil soilType,
                  Multiplying multiplying, VisualParameters visualParameters, GrowingTips growingTips) {
        this.id = id;
        this.name = name;
        this.plantingDate = plantingDate;
        this.origin = origin;
        this.soilType = soilType;
        this.multiplying = multiplying;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Soil getSoilType() {
        return soilType;
    }

    public void setSoilType(Soil soilType) {
        this.soilType = soilType;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
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
        Flower flower = (Flower) o;
        if (id == null) {
            if (flower.id != null) {
                return false;
            }
        } else if (!id.equals(flower.id)) {
            return false;
        }
        if (name == null) {
            if (flower.name != null) {
                return false;
            }
        } else if (!name.equals(flower.name)) {
            return false;
        }

        if (plantingDate == null) {
            if (flower.plantingDate != null) {
                return false;
            }
        } else if (!plantingDate.equals(flower.plantingDate)) {
            return false;
        }
        if (origin == null) {
            if (flower.origin != null) {
                return false;
            }
        } else if (!origin.equals(flower.origin)) {
            return false;
        }
        if (soilType != flower.soilType) {
            return false;
        }

        if (multiplying != flower.multiplying) {
            return false;
        }
        if (visualParameters == null) {
            if (flower.visualParameters != null) {
                return false;
            }
        } else if (!visualParameters.equals(flower.visualParameters)) {
            return false;
        }
        if (growingTips == null) {
            if (flower.growingTips != null) {
                return false;
            }
        } else if (growingTips.equals(flower.growingTips)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (id != null ? id.hashCode() : 0);
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (plantingDate != null ? plantingDate.hashCode() : 0);
        result = prime * result + (origin != null ? origin.hashCode() : 0);
        result = prime * result + (soilType != null ? soilType.hashCode() : 0);
        result = prime * result + (multiplying != null ? multiplying.hashCode() : 0);
        result = prime * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        result = prime * result + (growingTips != null ? growingTips.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flower{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", plantingDate=").append(plantingDate);
        sb.append(", origin='").append(origin).append('\'');
        sb.append(", soilType=").append(soilType);
        sb.append(", multiplying=").append(multiplying);
        sb.append(", visualParameters=").append(visualParameters);
        sb.append(", growingTips=").append(growingTips);
        sb.append('}');
        return sb.toString();
    }
}
