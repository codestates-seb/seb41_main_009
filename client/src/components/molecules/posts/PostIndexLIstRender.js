import styled from 'styled-components';
import { Icon } from '@iconify/react';
import { LabelMedium } from '../../../styles/typo';

const IndexLabeldepth1 = styled.h1`
  display: flex;
  flex-direction: row;
  padding-left: 5px;
  ${LabelMedium}
  color: var(--gray-500);
  gap: 6px;

  &:hover {
    color: var(--gray-700);
  }
`;
const IndexLabeldepth2 = styled.h2`
  display: flex;
  flex-direction: row;
  padding-left: 20px;
  color: var(--gray-500);
  ${LabelMedium}
  gap: 6px;

  &:hover {
    color: var(--gray-700);
  }
`;
const IndexLabeldepth3 = styled.h3`
  display: flex;
  flex-direction: row;
  padding-left: 40px;
  color: var(--gray-500);
  gap: 6px;

  ${LabelMedium}
  &:hover {
    color: var(--gray-700);
  }
`;

const PostIndexLIstRender = ({ item }) => {
  return (
    <div>
      {item.htmlTag && item.htmlTag === 'h1' ? (
        <IndexLabeldepth1>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </IndexLabeldepth1>
      ) : (
        ''
      )}
      {item.htmlTag && item.htmlTag === 'h2' ? (
        <IndexLabeldepth2>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </IndexLabeldepth2>
      ) : (
        ''
      )}
      {item.htmlTag && item.htmlTag === 'h3' ? (
        <IndexLabeldepth3>
          {item.content}
          {item.image ? <Icon icon="mdi:image-outline" style={{ fontSize: '20px' }} /> : null}
        </IndexLabeldepth3>
      ) : (
        ''
      )}
    </div>
  );
};

export default PostIndexLIstRender;
