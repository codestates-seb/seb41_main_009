import axios from 'axios';
import { useState, useEffect } from 'react';
import { seriesListDummy } from '../constants/dummyData';
import HOST from '../constants/URL';

/**
 *
 * @param {string} category
 * @param {string} page
 * @returns {seriesList, seriesPageInfo}
 */
const useGetSeriesList = (category, page) => {
  const [seriesList, setSeriesList] = useState([]);
  const [seriesPageInfo, setSeriesPageInfo] = useState({});

  const URL = `${HOST}/categories/${category}/series?page=${page}&size=10`;

  useEffect(() => {
    axios
      .get(URL)
      .then(({ data, pageInfo }) => {
        setSeriesList(data);
        setSeriesPageInfo(pageInfo);
      })
      .catch(err => console.log(err))
      .finally(() => {
        const { data, pageInfo } = seriesListDummy;
        setSeriesList(data);
        setSeriesPageInfo(pageInfo);
      });
  }, []);

  return { seriesList, seriesPageInfo };
};

export default useGetSeriesList;
