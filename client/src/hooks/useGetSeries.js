import axios from 'axios';
import { useState } from 'react';
// import { SeriesDummy } from '../constants/dummyData';

/**
 *
 * @param {string | number} seriesId
 * @returns {series{}, boolean, boolean}
 * 특정한 시리즈 정보를 리턴
 */
const useGetSeries = () => {
  const [series, setSeries] = useState({});

  const getSeries = seriesId => {
    const url = `series/${seriesId}`;
    axios
      .get(url)
      .then(res => {
        setSeries(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  };

  return { series, getSeries };
};

export default useGetSeries;
