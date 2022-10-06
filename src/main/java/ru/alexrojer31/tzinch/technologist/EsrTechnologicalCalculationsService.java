package ru.alexrojer31.tzinch.technologist;

import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.castBlankConfigs.CastBlankConfig;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.consumableElectrodeConfigs.ConsumableElectrodeConfig;
import ru.alexrojer31.tzinch.technologist.semiFinishedProductConfigs.pipeConfigs.PipeConfig;

public class EsrTechnologicalCalculationsService {

    public static final double SHRINKAGE_RATIO = 1.025;
    public static final int COEFFICIENT = 2;
    public static final int STEEL_DENSITY = 7850;

    public int calculateCastDiameter(int crystallizerDiameter) {
        return (int) (crystallizerDiameter / SHRINKAGE_RATIO - COEFFICIENT);
    }

    public int calculateCastDiameter(CastBlankConfig castBlankConfig) {
        int crystallizerDiameter = castBlankConfig.getCrystallizerDrawing()
                .getDiameter();
        return calculateCastDiameter(crystallizerDiameter);
    }

    public int calculateCastWall(int crystallizerDiameter, int mandrelDiameter) {
        return (int) ((crystallizerDiameter - mandrelDiameter) / 2 / SHRINKAGE_RATIO - COEFFICIENT);
    }

    public int calculateCastWall(CastBlankConfig castBlankConfig) {
        int crystallizerDiameter = castBlankConfig.getCrystallizerDrawing().getDiameter();
        int mandrelDiameter = castBlankConfig.getMandrelDrawing().getDiameter();
        return calculateCastWall(crystallizerDiameter, mandrelDiameter);
    }

    public int calculateFilling(int crystallizerDiameter,
                                int mandrelDiameter,
                                int electrodeDiameter,
                                int numberOfElectrodes) {
        int diameter = calculateCastDiameter(crystallizerDiameter);
        int wall = calculateCastWall(crystallizerDiameter, mandrelDiameter);
        double S = Math.PI * wall * (diameter - wall);
        double s = (Math.PI * electrodeDiameter * electrodeDiameter / 4 ) * numberOfElectrodes;
        return (int) ((s / S) * 100);
    }

    public int calculateFilling(CastBlankConfig castBlankConfig) {
        int crystallizerDiameter = castBlankConfig.getCrystallizerDrawing().getDiameter();
        int mandrelDiameter = castBlankConfig.getMandrelDrawing().getDiameter();
        int electrodeDiameter = castBlankConfig.getConsumableElectrodeConfig().getConductorDrawing().getDiameter();
        int numberOfElectrodes = castBlankConfig.getConsumableElectrodeConfig().getConductorDrawing().getQuantity();
        return calculateFilling(crystallizerDiameter,
                mandrelDiameter,
                electrodeDiameter,
                numberOfElectrodes);
    }

    public int calculateCastMedianDiameter(int crystallizerDiameter, int mandrelDiameter) {
        return (crystallizerDiameter + mandrelDiameter) / 2;
    }

    public int calculateCastMedianDiameter(CastBlankConfig castBlankConfig) {
        int crystallizerDiameter = castBlankConfig.getCrystallizerDrawing().getDiameter();
        int mandrelDiameter = castBlankConfig.getMandrelDrawing().getDiameter();
        return calculateCastMedianDiameter(crystallizerDiameter, mandrelDiameter);
    }

    public int calculatePipeWeight(int pipeDiameter, int pipeWall, int pipeLength) {
        return (int) ((Math.PI * pipeWall * (pipeDiameter -pipeWall) * pipeLength * STEEL_DENSITY) / 1000000000);
    }

    public int calculatePipeWeight(PipeConfig pipeConfig, int pipeLength) {
        int pipeDiameter = pipeConfig.getDiameter();
        int pipeWall = pipeConfig.getWall();
        return calculatePipeWeight(pipeDiameter, pipeWall, pipeLength);
    }

    public int calculateCastWeight(int crystallizerDiameter, int mandrelDiameter, int castLength) {
        int castWall = calculateCastWall(crystallizerDiameter, mandrelDiameter);
        int castDiameter = calculateCastDiameter(crystallizerDiameter);
        return (int) ((Math.PI * castWall * (castDiameter -castWall) * castLength * STEEL_DENSITY) / 1000000000);
    }

    public int calculateCastWeight(CastBlankConfig castBlankConfig, int castLength) {
        int crystallizerDiameter = castBlankConfig.getCrystallizerDrawing().getDiameter();
        int mandrelDiameter = castBlankConfig.getMandrelDrawing().getDiameter();
        return calculateCastWeight(crystallizerDiameter, mandrelDiameter, castLength);
    }

    public int calculateElectrodeWeight(int electrodeDiameter,
                                        int electrodeNumber,
                                        int electrodeLength) {
        return (int) ((Math.PI * electrodeDiameter * electrodeDiameter / 4 * electrodeNumber * electrodeLength * STEEL_DENSITY) / 1000000000);
    }

    public int calculateElectrodeWeight(ConsumableElectrodeConfig consumableElectrodeConfig, int electrodeLength) {
        int electrodeDiameter = consumableElectrodeConfig.getConductorDrawing().getDiameter();
        int electrodeNumber = consumableElectrodeConfig.getConductorDrawing().getQuantity();
        return calculateElectrodeWeight(electrodeDiameter, electrodeNumber, electrodeLength);
    }

    public int calculateOutsideAllowance(int crystallizerDiameter, int pipeDiameter) {
        return (calculateCastDiameter(crystallizerDiameter) - pipeDiameter) / 2;
    }

    public int calculateOutsideAllowance(PipeConfig pipeConfig) {
        int crystallizerDiameter = pipeConfig.getCastBlankConfig().getCrystallizerDrawing().getDiameter();
        int pipeDiameter = pipeConfig.getDiameter();
        return calculateOutsideAllowance(crystallizerDiameter, pipeDiameter);
    }

    public int calculateInsideAllowance(int crystallizerDiameter,
                                        int mandrelDiameter,
                                        int pipeDiameter,
                                        int pipeWall) {
        int castWall = calculateCastWall(crystallizerDiameter, mandrelDiameter);
        int outsideAllowance = calculateOutsideAllowance(crystallizerDiameter, pipeDiameter);
        return castWall - outsideAllowance - pipeWall;
    }

    public int calculateInsideAllowance(PipeConfig pipeConfig) {
        int crystallizerDiameter = pipeConfig.getCastBlankConfig().getCrystallizerDrawing().getDiameter();
        int mandrelDiameter = pipeConfig.getCastBlankConfig().getMandrelDrawing().getDiameter();
        int pipeDiameter = pipeConfig.getDiameter();
        int pipeWall = pipeConfig.getWall();
        return calculateInsideAllowance(crystallizerDiameter, mandrelDiameter, pipeDiameter, pipeWall);
    }

    public double calculateYieldRatio(PipeConfig pipeConfig) {
        double castWeight = calculateCastWeight(pipeConfig.getCastBlankConfig(), 1000);
        double pipeWeight = calculatePipeWeight(pipeConfig, 1000);
        double var = Math.round(pipeWeight / castWeight * 100);
        return var / 100;
    }
}
