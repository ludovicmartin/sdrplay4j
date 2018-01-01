package com.mirics.sdrplay;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import java.util.Arrays;
import java.util.List;

/**
 * SDRplay library. <br>All functions are blocking and not thread-safe.<br><br>
 *
 * Copyright (c) 2013 Mirics Ltd, All Rights Reserved Legal Information<br><br>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:<br>
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.<br>2. Redistributions
 * in binary form must reproduce the above copyright notice, this list of
 * conditions and the following discla imer in the documentation and/or other
 * materials provided with the distribution.<br>3. Neither the name of the
 * copyright holder nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written
 * permission.<br><br>THIS SOFTWARE IS PROVID ED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUS ED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.<br>SDRPlay modules use a Mirics
 * chip set and software. The information supplied hereunder is provided to you
 * by SDRPlay under license from Mirics. Mirics hereby grants you a perpetual,
 * worldwide, royalty free license to use the information herei n for the
 * purpose of designing software that uti lizes SDRPlay modules, under the
 * following conditions:<br>There are no express or implied copyright licenses
 * granted hereunder to design or fabricate any integrated circuits or
 * integrated circuits based on the information in this document. Mirics
 * reserves th e right to make changes without further notice to any of its
 * products. Mirics makes no warranty, representation or guarantee regarding the
 * suitability of its products for a ny particular purpose, nor does Mirics
 * assume any liability arising out of the appli cation or use of any product or
 * circuit, and specifically disclaims any and all liability, including without
 * limitation consequential or incidental damages. Typical param eters that may
 * be provided in Mirics data sheets and/or specifications can and do vary in
 * different applications and actual performance may vary over time. All
 * operating parameters must be validated for each customer application by the
 * buyer’s technical experts. SDRPlay and Mirics products are not designed,
 * intended, or authorized for use a s components in systems intended for
 * surgical implant into the body, or other applications intended to support or
 * sustain life, or for any other appl ication in which the failure of the
 * Mirics product could create a situation where personal injury or death may
 * occur. Should Buyer purchase or use SDRPlay or Mirics products for any such
 * unintended or unauthorized application, Buyer shall indemnify and hold both
 * SDRPlay and Mirics and their officers, employees, subsidiaries, affiliates,
 * and distributors harmles s against all claims, costs, damages, and expenses,
 * and reasonable attorney fees arising out of, directly or indirectly, any
 * claim of perso nal injury or death associated with such unintended or
 * unauthorized use, even if such claim alleges that either SDRPl ay or Mirics
 * were negligent regarding the design or manufacture of the part. Mirics
 * FlexiRF™, Mirics FlexiTV™ and Mirics™ are trademarks of Mirics.<br>SDRPlay is
 * the trading name of SDRPlay Limited a company registered in England #
 * 09035244. Mirics is the tr ading name of Mirics Limited a company registered
 * in England # 05046393
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public interface MirSdrApiRspLibrary extends Library {

    /**
     * Library instance.
     */
    public static final MirSdrApiRspLibrary INSTANCE = (MirSdrApiRspLibrary) Native.loadLibrary("mirsdrapi-rsp", MirSdrApiRspLibrary.class);

    /**
     * Error Code Enumerated Type.
     */
    public static interface mir_sdr_ErrT {

        /**
         * Successful completion.
         */
        public static final int mir_sdr_Success = 0;
        /**
         * Other failure mechanism (thread / mutex create).
         */
        public static final int mir_sdr_Fail = 1;
        /**
         * NULL pointers.
         */
        public static final int mir_sdr_InvalidParam = 2;
        /**
         * Requested parameters outside of allowed range.
         */
        public static final int mir_sdr_OutOfRange = 3;
        /**
         * Previous update has not yet been applied.
         */
        public static final int mir_sdr_GainUpdateError = 4;
        /**
         * Previous update has not yet been applied.
         */
        public static final int mir_sdr_RfUpdateError = 5;
        /**
         * Previous update has not yet been applied.
         */
        public static final int mir_sdr_FsUpdateError = 6;
        /**
         * Failed to access device.
         */
        public static final int mir_sdr_HwError = 7;
        /**
         * Requested parameters will cause aliasing.
         */
        public static final int mir_sdr_AliasingError = 8;
        /**
         * API has been initi alised previously.
         */
        public static final int mir_sdr_AlreadyInitialised = 9;
        /**
         * API has not been initialised.
         */
        public static final int mir_sdr_NotInitialised = 10;
        /**
         * The requested change has not been applied.
         */
        public static final int mir_sdr_NotEnabled = 11;
        /**
         * Incorrect device.
         */
        public static final int mir_sdr_HwVerError = 12;
        /**
         * Out of memory.
         */
        public static final int mir_sdr_OutOfMemError = 13;
    }

    /**
     * Band Width Enumerated Type.
     */
    public static interface mir_sdr_Bw_MHzT {

        public static final int mir_sdr_BW_Undefined = 0;
        public static final int mir_sdr_BW_0_200 = 200;
        public static final int mir_sdr_BW_0_300 = 300;
        public static final int mir_sdr_BW_0_600 = 600;
        public static final int mir_sdr_BW_1_536 = 1536;
        public static final int mir_sdr_BW_5_000 = 5000;
        public static final int mir_sdr_BW_6_000 = 6000;
        public static final int mir_sdr_BW_7_000 = 7000;
        public static final int mir_sdr_BW_8_000 = 8000;
    }

    /**
     * IF Enumerated Type.
     */
    public static interface mir_sdr_If_kHzT {

        public static final int mir_sdr_IF_Undefined = -1;
        public static final int mir_sdr_IF_Zero = 0;
        public static final int mir_sdr_IF_0_450 = 450;
        public static final int mir_sdr_IF_1_620 = 1620;
        public static final int mir_sdr_IF_2_048 = 2048;
    }

    /**
     * Transfer Mode Enumerated Type.
     */
    public static interface mir_sdr_TransferModeT {

        public static final int mir_sdr_ISOCH = 0;
        public static final int mir_sdr_BULK = 1;
    }

    /**
     * Reinit Enumerated Type.
     */
    public static interface mir_sdr_ReasonForReinitT {

        public static final int mir_sdr_CHANGE_NONE = 0x00;
        public static final int mir_sdr_CHANGE_GR = 0x01;
        public static final int mir_sdr_CHANGE_FS_FREQ = 0x02;
        public static final int mir_sdr_CHANGE_RF_FREQ = 0x04;
        public static final int mir_sdr_CHANGE_BW_TYPE = 0x08;
        public static final int mir_sdr_CHANGE_IF_TYPE = 0x10;
        public static final int mir_sdr_CHANGE_LO_MODE = 0x20;
        public static final int mir_sdr_CHANGE_AM_PORT = 0x40;
    }

    /**
     * LO Mode Enumerated Type.
     */
    public static interface mir_sdr_LoModeT {

        public static final int mir_sdr_LO_Undefined = 0;
        public static final int mir_sdr_LO_Auto = 1;
        public static final int mir_sdr_LO_120MHz = 2;
        public static final int mir_sdr_LO_144MHz = 3;
        public static final int mir_sdr_LO_168MHz = 4;
    }

    /**
     * Band Enumerated Type.
     */
    public static interface mir_sdr_BandT {

        public static final int mir_sdr_BAND_AM_LO = 0;
        public static final int mir_sdr_BAND_AM_MID = 1;
        public static final int mir_sdr_BAND_AM_HI = 2;
        public static final int mir_sdr_BAND_VHF = 3;
        public static final int mir_sdr_BAND_3 = 4;
        public static final int mir_sdr_BAND_X = 5;
        public static final int mir_sdr_BAND_4_5 = 6;
        public static final int mir_sdr_BAND_L = 7;
    }

    /**
     * Gain Reduction Update Mode Enumerated Type.
     */
    public static interface mir_sdr_SetGrModeT {

        public static final int mir_sdr_USE_SET_GR = 0;
        public static final int mir_sdr_USE_SET_GR_ALT_MODE = 1;
        public static final int mir_sdr_USE_RSP_SET_GR = 2;
    }

    /**
     * RSP2 Band Enumerated Type.
     */
    public static interface mir_sdr_RSPII_BandT {

        public static final int mir_sdr_RSPII_BAND_UNKNOWN = 0;
        public static final int mir_sdr_RSPII_BAND_AM_LO = 1;
        public static final int mir_sdr_RSPII_BAND_AM_MID = 2;
        public static final int mir_sdr_RSPII_BAND_AM_HI = 3;
        public static final int mir_sdr_RSPII_BAND_VHF = 4;
        public static final int mir_sdr_RSPII_BAND_3 = 5;
        public static final int mir_sdr_RSPII_BAND_X_LO = 6;
        public static final int mir_sdr_RSPII_BAND_X_MID = 7;
        public static final int mir_sdr_RSPII_BAND_X_HI = 8;
        public static final int mir_sdr_RSPII_BAND_4_5 = 9;
        public static final int mir_sdr_RSPII_BAND_L = 10;
    }

    /**
     * RSP2 Antenna Select Enumerated Type.
     */
    public static interface mir_sdr_RSPII_AntennaSelectT {

        public static final int mir_sdr_RSPII_ANTENNA_A = 5;
        public static final int mir_sdr_RSPII_ANTENNA_B = 6;
    }

    /**
     * AGC Control Enumerated Type.
     */
    public static interface mir_sdr_AgcControlT {

        public static final int mir_sdr_AGC_DISABLE = 0;
        public static final int mir_sdr_AGC_100HZ = 1;
        public static final int mir_sdr_AGC_50HZ = 2;
        public static final int mir_sdr_AGC_5HZ = 3;
    }

    /**
     * Gain Message ID Enumerated Type.
     */
    public static interface mir_sdr_GainMessageIdT {

        public static final int mir_sdr_GAIN_MESSAGE_START_ID = 0x80000000;
        public static final int mir_sdr_ADC_OVERLOAD_DETECTED = mir_sdr_GAIN_MESSAGE_START_ID + 1;
        public static final int mir_sdr_ADC_OVERLOAD_CORRECTED = mir_sdr_GAIN_MESSAGE_START_ID + 2;
    }

    /**
     * Minimum IF Gain Reduction Enumerated Type.
     */
    public static interface mir_sdr_MinGainReductionT {

        public static final int mir_sdr_EXTENDED_MIN_GR = 0;
        public static final int mir_sdr_NORMAL_MIN_GR = 20;
    }

    /**
     * Device Enumeration Structure. Used to pass back RSP device information in
     * mir_sdr_GetDevices(). All RSPx devices will be listed, but if a device is
     * already in use, the devAvail flag will be set to 0 (instead of 1 if it is
     * available).
     */
    public static class mir_sdr_DeviceT extends Structure {

        public Pointer SerNo = new Memory(128);
        public Pointer DevNm = new Memory(128);
        public byte hwVer;
        public byte devAvail;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(
                    "SerNo",
                    "DevNm",
                    "hwVer",
                    "devAvail"
            );
        }

        public mir_sdr_DeviceT() {
        }

        public mir_sdr_DeviceT(Pointer p) {
            super(p);
        }

        public static class ByReference extends mir_sdr_DeviceT implements Structure.ByReference {
        }

    }

    /**
     * Gain Values Structure. Used to pass back current gain settings in
     * mir_sdr_GetCurrentGain().
     */
    public static class mir_sdr_GainValuesT extends Structure {

        public float curr;
        public float max;
        public float min;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(
                    "curr",
                    "max",
                    "min"
            );
        }

        public mir_sdr_GainValuesT() {
        }

        public mir_sdr_GainValuesT(Pointer p) {
            super(p);
        }

        public static class ByReference extends mir_sdr_GainValuesT implements Structure.ByReference {
        }

    }

    /**
     * Stream callback. This callback is triggered when there are samples to be
     * processed.
     * <br>C prototype:
     * <code>typedef void (*mir_sdr_StreamCallback_t)(short *xi, short *xq, unsigned int firstSampleNum, int grChanged, int rfChanged, int fsChanged, unsigned int numSamples, unsigned int reset, void *cbContext);</code>
     */
    public interface mir_sdr_StreamCallback_t extends Callback {

        /**
         * Stream callback.This callback is triggered when there are samples to
         * be processed.
         *
         * @param xi Pointer to real data in the buffer.
         * @param xq Pointer to the imaginary data in the buffer.
         * @param firstSampleNum Number of first sample in buffer (used for
         * synchronous updates). Note that this is divided in the API by the
         * <code>decimationFactor</code> , to make it re lative to the output
         * sample rate. The values specified for sample number and period for
         * synchronous updates should also be relative to the output sample
         * rate.
         * @param grChanged Indicates when the gain reduction has changed:<br>
         * <code>
         * Bit0 &rarr; Change status of 1st packet: 0=&gt; no change, 1 =&gt; change occurred
         * <br>Bit1 &rarr; Change status of 2nd packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * <br>Bit2 &rarr; Change status of 3rd packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * <br>Bit3 &rarr; Change status of 4th packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * </code>
         * @param rfChanged Indicates when the tuner frequency has changed:<br>
         * <code>
         * Bit0 &rarr; Change status of 1st packet: 0=&gt; no change, 1 =&gt; change occurred
         * <br>Bit1 &rarr; Change status of 2nd packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * <br>Bit2 &rarr; Change status of 3rd packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * <br>Bit3 &rarr; Change status of 4th packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * </code>
         * @param fsChanged Indicates when the sample frequency has changed:<br>
         * <code>
         * Bit0 &rarr; Change status of 1st packet: 0=&gt; no change, 1 =&gt; change occurred
         * <br>Bit1 &rarr; Change status of 2nd packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * <br>Bit2 &rarr; Change status of 3rd packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * <br>Bit3 &rarr; Change status of 4th packet: 0=&gt; no change, 1
         * =&gt; change occurred
         * </code>
         * @param numSamples The number of samples in the current buffer.
         * @param reset Indicates if a re-initialisation has occurred and that
         * the local buffering should be reset.
         * @param cbContext Pointer to context passed into
         * <code>mir_sdr_StreamInit()</code>.
         */
        void invoke(Pointer xi, Pointer xq, int firstSampleNum, int grChanged, int rfChanged, int fsChanged, int numSamples, int reset, Pointer cbContext);
    }

    /**
     * Gain change callback. This callback is triggered when ever a gain update
     * occurs.
     * <br>C prototype:
     * <code>typedef void (*mir_sdr_GainChangeCallback_t)(unsigned int gRidx, unsigned int gRdB, unsigned int lnaGRdB, void *cbContext);</code>
     */
    public interface mir_sdr_GainChangeCallback_t extends Callback {

        /**
         * Gain change callback. This callback is triggered when ever a gain
         * update occurs.
         *
         * @param gRdB New IF gain reduction value applied by the gain update.
         * This parameter is also used to pass m essages from API to the
         * application as defined in section 2.12.
         * @param lnaGRdB LNA gain reduction value.
         * @param cbContext Pointer to context passed into
         * <code>mir_sdr_StreamInit()</code>.
         */
        void invoke(int gRdB, int lnaGRdB, Pointer cbContext);
    }

    /**
     * Initiates the API for the specified tuner frequency.<br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_Init(int gRdB, double fsMHz, double rfMHz, mir_sdr_Bw_MHzT bwType, mir_sdr_If_kHzT ifType, int *samplesPerPacket);</code>
     *
     * @deprecated these functions are no longer supported. They will still work
     * but will be removed in a future version o f the API.
     * @param gRdB Inital g ain reduction (see gain reduction tables referenced
     * in section 5.1 for ranges and mappings).
     * @param fsMHz Specifies the sample frequency in MHz, values between 2MHz
     * and 10MHz are permitted. Decimation can be used to obtai n lower sample
     * rates.
     * @param rfMHz Specifies the tuner frequency in MHz, see frequency
     * allocation tables, section 0 .
     * @param bwType Specifies the bandwidth to be used, see list in enumerated
     * type for supported modes (see mir_sdr_Bw_MHzT).
     * @param ifType Specifies the IF to be used, see list in enumerated type
     * for supported modes. (see mir_sdr_If_kHzT)
     * @param samplesPerPacket Pointer to an unsigned integer which returns the
     * number of samples that will be returned in each call to
     * <code>mir_sdr_ReadPacket()</code>.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_Init(int gRdB, double fsMHz, double rfMHz, int bwType, int ifType, IntByReference samplesPerPacket);

    /**
     * Uninitialises the API.<br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_Uninit(void);</code>
     *
     * @deprecated these functions are no longer supported. They will still work
     * but will be removed in a future version o f the API.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_Uninit();

    /**
     * This function checks that the version in the include fi le is consistent
     * with the dll version.<br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_ApiVersion(float *version);</code>
     *
     * @param version Pointer to a float which returns the version of the dll.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_ApiVersion(FloatByReference version);

    /**
     * Used to enable debug message output.<br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_DebugEnable(unsigned int enable);</code>
     *
     * @param enable Debug output control (0 = messages disabled (default), 1 =
     * messages enabled).
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_DebugEnable(int enable);

    /**
     * Returns list of RSP 1, RSP2 and RSP1A devices along with the serial
     * number and unique reference (DevNm) and type.<br>The index in the
     * returned list is used with <code>mir_sdr_SetDeviceIdx()</code> to select
     * which device to use. These functions must be called prior to
     * <code>mir_sdr_StreamInit()</code> or <code>mir_sdr_ Init()</code>
     * otherwise the first device in the list will automatically be chosen. All
     * RSPx devices will be listed in the <code>devices</code> structure up to
     * the maximum number of entries in the table as specified by
     * <code>maxDevs</code>, with the actual number in the list being returned
     * in <code>numDevs</code>. However, if a device is already in use, the
     * <code>devAvail</code> flag will be set to 0 (instead of 1 if it is
     * available).
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_GetDevices(mir_sdr_DeviceT *devices, unsigned int *numDevs, unsigned int maxDevs);</code>
     *
     * @param devices Pointer to array of <code>mir_sdr_DeviceT</code>
     * structures allocated in the application. The array must be able to
     * contain at least <code>maxDevs</code> structures.
     * @param numDevs Pointer to variable which on return will indicate t he
     * number of devices found (or <code>maxDevs</code> if there are more than
     * the size of array allocated).
     * @param maxDevs Maximum number of devices to be returned.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_GetDevices(mir_sdr_DeviceT.ByReference devices, IntByReference numDevs, int maxDevs);

    /**
     * Returns the hardware version of the device currently in use.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_GetHwVersion(unsigned char *ver);</code>
     *
     * @param version Pointer to variable that on return will contain the
     * version of the device in use.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_GetHwVersion(Pointer version);

    /**
     * Used to select which device t o open. Prior to each call to this
     * function, a call must be made to <code>mir_sdr_GetDevices()</code>.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetDeviceIdx(unsigned int idx);</code>
     *
     * @param index Index of device to be opened. This is the array index of the
     * structures returned in a prior call to <code>mir_sdr_GetDevices()</code>.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_SetDeviceIdx(int index);

    /**
     * To specify a correction factor used to account for offsets from the
     * nominal in the crystal oscillator.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetPpm(double ppm);</code>
     *
     * @param ppm Parts per million offset (e.g. +/- 1 ppm specifies a +/- 24Hz
     * error for a 24MHz crystal).
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_SetPpm(double ppm);

    /**
     * Selects which antenna to use on an RSP2 device. If the device is already
     * opened, it will change the port immediately, otherwise it will take
     * effect on in itialisation (if selected device is an RSP2).<br>Note, when
     * operating in the AM band, the <code>mir_sdr_AmPortSelect()</code> API
     * function may be used to select the Hi - Z input instead of Antenna A or
     * Antenna B.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_RSPII_AntennaControl(mir_sdr_RSPII_AntennaSelectT select);</code>
     *
     * @param select Selects antenna: (mir_sdr_RSPII_ANTENNA_A = Antenna A,
     * mir_sdr_RSPII_ANTENNA_B = Antenna B).
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_RSPII_AntennaControl(int select);

    /**
     * Used to select which AM port to use on a RSP2 device, when the requested
     * tuner frequency is within the AM band. This function must be called
     * before the device is initialised, otherwise it will not have any effect.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_AmPortSelect(int port);</code>
     *
     * @param port AM port control: (0 = Port 0 (Antenna A or Antenna B inputs -
     * default), 1 = Port 1 (H i - Z input)).
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_AmPortSelect(int port);

    /**
     * Replaces <code>mir_sdr_Init()</code>. It sets up a thread (or chain of
     * threads) inside the API which will perform the processing chain, and then
     * use the callback function to return the data to the calling application.
     * Processing chain (in order):
     * <br><code>ReadPacket()</code> fetch packet of IQ samples from USB
     * interface
     * <br><code>Agc()</code> disabled by default
     * <br><code>DCoffsetCorrection()</code> enabled by default in LIF mode
     * (otherwise disabled)
     * <br><code>DownConvert()</code> automatically enabled if the parameters
     * shown in section 0 are matched
     * <br><code>Decimate()</code> disabled by default
     * <br><code>DCoffsetCorrection()</code> enabled by default in ZIF mode
     * (otherwise disabled)
     * <br><code>IQimbalanceCorrection()</code> enabled by default
     * <br>This function will set the default tuner based DC correction
     * parameters to Periodic 3 with Speedup disabled - see
     * <code>mir_sdr_SetDcMode()</code> for more details.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_StreamInit(int *gRdB, double fsMHz, double rfMHz, mir_sdr_Bw_MHzT bwType, mir_sdr_If_kHzT ifType, int LNAstate, int *gRdBsystem, mir_sdr_SetGrModeT setGrMode, int *samplesPerPacket, mir_sdr_StreamCallback_t StreamCbFn, mir_sdr_GainChangeCallback_t GainChangeCbFn, void *cbContext);</code>
     *
     * @param gRdB Input value is the requested initial gain reduction in dB
     * (see gain reduction tables referenced in section 5 for ranges and
     * mappings), returns IF gain reduction value.
     * @param fsMHz Specifies the sample frequency in MHz, values between 2MHz
     * and 10MHz are permitted. Decimation can be used to obtain lower sample
     * rates.
     * @param rfMHz Specifies the tuner frequency in MHz, see frequency
     * allocation tables, section 0.
     * @param bwType Specifies the bandwidth to be used, see list in enumerated
     * type for supported modes.
     * @param ifType Specifies the IF to be used, see list in enumerated type
     * for supported modes.
     * @param LNAstate Specifies the LNA state:
     * <br>N/A &rarr; if <code>setGrMode == mir_sdr_USE_SET_GR</code>
     * <br>[0:1] &rarr; if <code>setGrMode == mir_sdr_USE_SET_GR_ALT_MODE</code>
     * <br>[0:3] &rarr; if
     * <code>setGrMode == mir_sdr_USE_RSP_SET_GR &amp;&amp; RSP1</code>
     * <br>[0:4] &rarr; if <code>setGrMode == mir_sdr_USE_RSP_SET_GR &amp;&amp;
     * AMport1 enabled &amp;&amp; RSP2</code>
     * <br>[0:5] &rarr; if <code>setGrMode == mir_sdr_USE_RSP_SET_GR &amp;&amp; Frf
     * &gt;= 420MHz &amp;&amp; RSP2</code>
     * <br>[0:8] &rarr; if <code>setGrMode == mir_sdr_USE_RSP_SET_GR &amp;&amp; Frf
     * &lt; 420MHz &amp;&amp; RSP2</code>
     * @param gRdBsystem Input value ignored, returns overall system gain
     * reduction value (if <code>setGrMode != mir_sdr_USE_SET_GR</code>).
     * @param setGrMode Specifies gain mode to use:
     * <br><code>mir_sdr_USE_SET_GR</code> &rarr; use
     * <code>mir_sdr_SetGr()</code>
     * <br><code>mir_sdr_USE_SET_GR_ALT_MODE</code> &rarr; use
     * <code>mir_sdr_SetGrAltMode()</code>
     * <br><code>mir_sdr_USE_RSP_SET_GR</code> &rarr; use
     * <code>mir_sdr_RSP_SetGr()</code>
     * @param samplesPerPacket Pointer to an unsigned integer which returns the
     * number of samples that will be returned in the callback for the current
     * configuration (may be modified by decimation rates). This will be a four
     * times the value of samplesPerPacket return ed by
     * <code>mir_sdr_Init()</code> as there is additional buffering in the
     * processing thread.
     * @param StreamCbFn Specifies the callback function to use to send
     * processed data.
     * @param GainChangeCbFn Specifies the callback function to use when an AGC
     * gain change happens to notify the application of the current gain
     * reduction settings.
     * @param cbContext Pointer to a context passed that will be returned as a
     * parameter in the callbacks.
     * @return error code value (see mir_sdr_ErrT)
     */
    public int mir_sdr_StreamInit(IntByReference gRdB, double fsMHz, double rfMHz, int bwType, int ifType, int LNAstate, IntByReference gRdBsystem, int setGrMode, IntByReference samplesPerPacket, mir_sdr_StreamCallback_t StreamCbFn, mir_sdr_GainChangeCallback_t GainChangeCbFn, Pointer cbContext);

    /**
     * Used to control whether AGC is enabled or not and parameters to allow the
     * AGC to be configured. Note: Requires internal stream thread to have been
     * created via <code>mir_sdr_StreamInit()</code> for AGC to be enabled.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_AgcControl(mir_sdr_AgcControlT enable, int setPoint_dBfs, int knee_dBfs, unsigned int decay_ms, unsigned int hang_ms, int syncUpdate, int LNAstate);</code>
     *
     * @param enable Specifies the AGC mode required. See enumerated types for
     * valid values. Default mode is 100Hz (<code>mir_sdr_100HZ</code>).
     * @param setPoint_dBfs Specifies the required set point in dBfs.
     * @param knee_dBfs Not currently used, set to 0.
     * @param decay_ms Not currently used, set to 0.
     * @param hang_ms Not currently used, set to 0.
     * @param syncUpdate Update control:<br>0 &rarr; immediate update<br>1
     * &rarr; synchronous update (see <code>mir_sdr_SetGR()</code> for more
     * details)
     * @param LNAstate Indicates the LNA state to use in gain updates when AGC
     * uses <code>mir_sdr_SetGrAltMode()</code> or
     * <code>mir_sdr_RSP_SetGr()</code> as specified when calling
     * <code>mir_sdr_StreamInit()</code>.
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_AgcControl(int enable, int setPoint_dBfs, int knee_dBfs, int decay_ms, int hang_ms, int syncUpdate, int LNAstate);

    /**
     * Used to enable the external reference output port on a RSP2 device . If
     * this is called before the device is opened (but after a device has been
     * selected with <code>mir_sdr_SetDeviceIdx()</code>), then the device will
     * be opened, the external reference enabled and the device closed again.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_RSPII_ExternalReferenceControl(unsigned int output_enable);</code>
     *
     * @param output_enable External reference output control:<br>0 &rarr;
     * output is disabled (default)<br>1 &rarr; output is enabled
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_RSPII_ExternalReferenceControl(int output_enable);

    /**
     * Enables the RF Notch Filter on a RSP2 device. If the device is already
     * opened, it will change the set tings immediately, otherwise it will take
     * effect on initialisation (if selected device is an RSP2).
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_RSPII_RfNotchEnable(unsigned int enable);</code>
     *
     * @param enable RF Notch Filter control:<br>0 &rarr; filter disabled
     * (default)
     * <br>1 &rarr; filter enabled
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_RSPII_RfNotchEnable(int enable);

    /**
     * Used to enable the BiasT network on a RSP2 device. If this is called
     * before the device is opened (but after a device has been selected with
     * <code>mir_sdr_SetDeviceIdx()</code>), then the device will be opened, the
     * BiasT network will be enabled and the device closed again.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_RSPII_BiasTControl(unsigned int enable);</code>
     *
     * @param enable BiasT enable control:<br>0 &rarr; BiasT disabled (default)
     * <br>1 &rarr; BiasT enabled
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_RSPII_BiasTControl(int enable);

    /**
     * Stops the stream and un initialises the API.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_StreamUninit(void);</code>
     *
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_StreamUninit();

    /**
     * Used to release a device previously selected using
     * <code>mir_sdr_SetDeviceIdx()</code>. If
     * <code>mir_sdr_SetDeviceIdx()</code> has been used, then this function
     * <u>must</u> be called prior to exiting the application.
     * <br>C prototype:
     * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_ReleaseDeviceIdx(void);</code>
     *
     * @return error code value (see <code>mir_sdr_ErrT</code>)
     */
    public int mir_sdr_ReleaseDeviceIdx();

}
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_ReadPacket(short *xi, short *xq, unsigned int *firstSampleNum, int *grChanged, int *rfChanged, int *fsChanged);</code>
 *
 * @deprecated these functions are no longer supported. They will still work but
 * will be removed in a future version o f the API.
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetRf(double drfHz, int abs, int syncUpdate);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetFs(double dfsHz, int abs, int syncUpdate, int reCal);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetGr(int gRdB, int abs, int syncUpdate);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetGrParams(int minimumGr, int lnaGrThreshold);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetDcMode(int dcCal, int speedUp);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetDcTrackTime(int trackTime);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetSyncUpdateSampleNum(unsigned int sampleNum);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetSyncUpdatePeriod(unsigned int period);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_ResetUpdateFlags(int resetGainUpdate, int resetRfUpdate, int resetFsUpdate);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetTransferMode(mir_sdr_TransferModeT mode);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_DownConvert(short *in, short *xi, short *xq, unsigned int samplesPerPacket, mir_sdr_If_kHzT ifType, unsigned int M, unsigned int preReset);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetParam(unsigned int id, unsigned int value);</code>
 *
 * @deprecated these functions are no longer supported. They will still work but
 * will be removed in a future version o f the API.
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetLoMode(mir_sdr_LoModeT loMode);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_SetGrAltMode(int *gRidx, int LNAstate, int *gRdBsystem, int abs, int syncUpdate);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_DCoffsetIQimbalanceControl(unsigned int DCenable, unsigned int IQenable);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype: <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_DecimateControl(unsigned int enable, unsigned int decimationFactor, unsigned int wideBandSignal);
 * </code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_Reinit(int *gRdB, double fsMHz, double rfMHz, mir_sdr_Bw_MHzT bwType, mir_sdr_If_kHzT ifType, mir_sdr_LoModeT loMode, int LNAstate, int *gRdBsystem, mir_sdr_SetGrModeT setGrMode, int *samplesPerPacket, mir_sdr_ReasonForReinitT reasonForReinit);</code>
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_GetGrByFreq(double rfMHz, mir_sdr_BandT *band, int *gRdB, int LNAstate, int *gRdBsystem, mir_sdr_SetGrModeT setGrMode);</code>
 *
 * @deprecated these functions are no longer supported. They will still work but
 * will be removed in a future version o f the API.
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */

/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_RSP_SetGr(int gRdB, int LNAstate, int abs, int syncUpdate);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_RSP_SetGrLimits(mir_sdr_MinGainReductionT minGr);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_rsp1a_BiasT(int enable);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_rsp1a_DabNotch(int enable);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_rsp1a_BroadcastNotch(int enable);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
/**
 * <br>C prototype:
 * <code>_MIR_SDR_QUALIFIER mir_sdr_ErrT mir_sdr_GetCurrentGain(mir_sdr_GainValuesT *gainVals);</code>
 *
 * @return error code value (see <code>mir_sdr_ErrT</code>)
 */
