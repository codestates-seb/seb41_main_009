import axios from 'axios';
import { useState, useEffect } from 'react';
import { SeriesDummy } from '../constants/dummyData';

/**
 *
 * @param {string | number} seriesId
 * @returns {series{}, boolean, boolean}
 * 특정한 시리즈 정보를 리턴
 */
const useGetSeries = ({ seriesId }) => {
  const [series, setSeries] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  const url = `series/${seriesId}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(url)
      .then(({ data }) => {
        setSeries(data);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        // 현재는 더미데이터에서 가져옴
        setSeries(SeriesDummy.data);
        setIsLoading(false);
        setIsLoadingError(false);
      });
  }, []);

  return { series, isLoading, isLoadingError };
};

export default useGetSeries;
