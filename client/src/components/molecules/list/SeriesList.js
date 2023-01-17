import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { AcrylicBase } from '../../atoms/AcrylicBase';
import { PostListStack } from './PostList';
import { ParagraphMedium, LabelListTitle, LabelMedium } from '../../../styles/typo';
import { UserInfoSmall } from '../UserInfo';
import { PARAGRAPH } from '../../../constants/Paragraph';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 10px;

  width: 1056px;
  height: fit-content;

  background: url(https://unsplash.it/1920/1080/?random) rgba(45, 45, 45, 0.44);
  border-radius: 30px;
  overflow: hidden;
`;

const InfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0px 0px;
  gap: 10px;

  width: fit-content;
  height: fit-content;
`;

const SeriesInfoLayer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 0px 0px;

  width: fit-content;
  height: fit-content;
`;

const SeriesPostNumLayer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 0px;
  gap: 20px;

  padding: 0px;
  gap: 20px;
  ${LabelMedium}
  color: #fff;

  width: fit-content;
  height: fit-content;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
`;

const ContextLayer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 5px 0px;
  gap: 10px;
  width: fit-content;
  height: fit-content;

  /* Inside auto layout */

  flex: none;
  order: 2;
  align-self: stretch;
  flex-grow: 0;
`;

const UserBox = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px;
  gap: 20px;

  width: fit-content;
  height: fit-content;
  color: #fff;
`;
const Title = styled.div`
  width: ${props => props.width || '326px'};
  height: fit-content;
  overflow: hidden;
  text-overflow: ellipsis;
  ${LabelListTitle}
  color: #fff;
  &:hover {
    color: var(--gray-100);
  }
`;
const Paragraph = styled.div`
  width: ${props => props.width || '326px'};
  height: 42px;
  overflow: hidden;
  text-overflow: ellipsis;

  ${ParagraphMedium}
  color: #fff;
  &:hover {
    color: var(--gray-100);
  }
`;

const SeriesList = ({ width, number = '10' }) => {
  const [series, setSeries] = useState({});
  const { seriesId } = useParams();

  useEffect(() => {
    const getData = async () => {
      await axios(`URL/${seriesId}`)
        .then(res => setSeries(res.data.data))
        .catch(error => console.log(error));
    };

    getData();
  }, [seriesId]);

  return (
    <Container>
      <AcrylicBase>
        <InfoLayer>
          <SeriesInfoLayer>
            <Title width={width}> {series.title || 'Series Title'} </Title>
            <SeriesPostNumLayer>
              <p>All Post</p>
              <p> {number} 개</p>
            </SeriesPostNumLayer>
          </SeriesInfoLayer>
          <Paragraph width={width}>{series.Paragraph || PARAGRAPH}</Paragraph>
          <ContextLayer>
            <UserBox>
              <UserInfoSmall name="UserName" image="https://unsplash.it/1920/1080/?random" />
            </UserBox>
          </ContextLayer>
        </InfoLayer>
        <PostListStack />
      </AcrylicBase>
    </Container>
  );
};

export default SeriesList;
